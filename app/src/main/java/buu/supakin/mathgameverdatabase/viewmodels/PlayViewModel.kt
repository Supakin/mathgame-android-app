package buu.supakin.mathgameverdatabase.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.*
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.Player
import buu.supakin.mathgameverdatabase.models.Question
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

        private const val DELAY_TIME = 3000L
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

    // Delay time
    private val _currentDelay = MutableLiveData<Long>()
    private val currentDelay: LiveData<Long>
        get() = _currentDelay

    val currentDelayString = Transformations.map(currentDelay) { time ->
        "โปรดรอตรวจคำตอบภายใน ${DateUtils.formatElapsedTime(time)} วินาที"
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

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    private val _eventClick = MutableLiveData<Boolean>()
    val eventClick: LiveData<Boolean>
        get() = _eventClick

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    private val _eventHide = MutableLiveData<Boolean>()
    val eventHide: LiveData<Boolean>
        get() = _eventHide

    init {
        viewModelScope.launch {
            _player.value = getPlayer(playerId)
            _menu.value = menu
            _question.value = Question(menu)
            _result.value = false
            _eventClick.value = false
            _eventHide.value = false
        }

        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentTime.value = DONE
                if (!_eventHide.value!!) onClick(question.value!!.randomPositionAnotherAnswer())
            }
        }

        timer.start()
    }

    private fun onCorrect () {
        viewModelScope.launch {
            _player.value?.onCorrect()
            update(_player.value!!)
        }
    }

    private fun onInCorrect () {
        viewModelScope.launch {
            _player.value?.onInCorrect()
            update(_player.value!!)
        }
    }

    private fun getResult () : Boolean {
        return if (_question.value?.getResult(answer.value?:0)!!) {
            onCorrect()
            true
        } else {
            onInCorrect()
            false
        }
    }

    fun onClick (index: Int) {
        _answer.value = _question.value!!.answerArray[index]
        _result.value = getResult()
        onHide()
        _eventClick.value = true
    }

    fun onClickComplete () {
        _eventClick.value = false
    }

    private fun onHide () {
        _eventHide.value = true
        val delay = object : CountDownTimer(DELAY_TIME, ONE_SECOND) {

            override fun onTick(millisUntilFinished: Long) {
                _currentDelay.value = millisUntilFinished/ONE_SECOND
            }

            override fun onFinish() {
                _currentDelay.value = DONE
                onNext()
            }
        }
        delay.start()
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }

    override fun onCleared() {
        super.onCleared()
        timer.cancel()
    }

    private suspend fun getPlayer(id: Long): Player? {
        return database.get(id)
    }

    private suspend fun update (player: Player) {
        database.update(player)
    }

}