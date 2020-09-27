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

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _score.value = score
        _menu.value = menu
        _question.value = Question(menu)
    }

    fun onCorrect () {
        _score.value?.onCorrect()
    }

    fun onInCorrect () {
        _score.value?.onInCorrect()
    }

    fun getResult (answer: Int) : Boolean {
        return _question.value?.getResult(answer)!!
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }

}