package buu.supakin.mathgameverdatabase.viewmodels

import android.os.CountDownTimer
import android.text.format.DateUtils
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverdatabase.models.Question
import buu.supakin.mathgameverdatabase.models.Score

class PlayViewModel (score : Score = Score(), menu: Int = 0) : ViewModel()  {
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

    private val _score = MutableLiveData<Score>()
    val score: LiveData<Score>
        get() = _score

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
        _score.value = score
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
        _score.value?.onCorrect()
    }

    private fun onInCorrect () {
        _score.value?.onInCorrect()
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

}