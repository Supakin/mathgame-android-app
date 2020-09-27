package buu.supakin.mathgameverviewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverviewmodel.models.Question
import buu.supakin.mathgameverviewmodel.models.Score

class PlayViewModel (score : Score = Score(), menu: Int = 0) : ViewModel()  {
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
    val answer: LiveData<Int>
        get() = _answer

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _score.value = score
        _menu.value = menu
        _question.value = Question(menu)
    }

    private fun onCorrect () {
        _score.value?.onCorrect()
    }

    private fun onInCorrect () {
        _score.value?.onInCorrect()
    }

    fun getResult () : Boolean {
        return if (_question.value?.getResult(_answer.value?:0)!!) {
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

}