package buu.supakin.mathgameverdatabase.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import buu.supakin.mathgameverdatabase.createPlayer
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.PlayerTable
import buu.supakin.mathgameverdatabase.models.Player
import buu.supakin.mathgameverdatabase.models.Question
import buu.supakin.mathgameverdatabase.models.Score
import kotlinx.coroutines.launch

class PlayViewModel (private val database: PlayerDatabaseDao, playerId: Long, menu: Int = 0) : ViewModel()  {
    private val playerId: Long = playerId

    companion object {
        // Time when the game is over
        private const val DONE = 0L
        // Countdown time interval
        private const val ONE_SECOND = 1000L
        // Total time for the game
        private const val COUNTDOWN_TIME = 30000L
    }

    // Countdown time
    private val _currentTime = MutableLiveData<Long>()
    private val currentTime: LiveData<Long>
        get() = _currentTime

    private val timer: CountDownTimer

    // The String version of the current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player>
        get() = _player

    private val _menu = MutableLiveData<Int>()
    val menu: LiveData<Int>
        get() = _menu

    private val _question = MutableLiveData<Question>()
    val question: LiveData<Question>
        get() = _question

    private val _answer = MutableLiveData<Int>()
    private val answer: LiveData<Int>
        get() = _answer

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        initPlayer()
        _menu.value = menu
        _question.value = Question(menu)

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND

            }

            override fun onFinish() {
                _currentTime.value = DONE
                onNext(question.value!!.randomPositionAnotherAnswer())
            }
        }

        timer.start()
    }

    private fun onCorrect () {
        _player.value?.onCorrect()
    }

    private fun onInCorrect () {
        _player.value?.onInCorrect()
    }

    fun getResult () : Boolean {
        return if (_question.value?.getResult(answer.value?:0)!!) {
            onCorrect()
            true
        } else {
            onInCorrect()
            false
        }
    }

    fun onNext (index: Int) {
        _answer.value = _question.value!!.answerArray[index]
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private suspend fun getPlayer(id: Long): PlayerTable? {
        return database.get(id)
    }

    private fun initPlayer () {
        viewModelScope.launch {
            val playerTable = getPlayer(playerId)
            _player.value = playerTable?.let { createPlayer(it) }
        }
    }

}