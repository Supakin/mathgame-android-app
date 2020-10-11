package buu.supakin.mathgameverdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverdatabase.models.Score

class ResultViewModel (score: Score = Score(), menu: Int = 0,result: Boolean = true): ViewModel() {
    private val _score = MutableLiveData<Score>()
    val score: LiveData<Score>
        get() = _score

    private val _menu = MutableLiveData<Int>()
    val menu: LiveData<Int>
        get() = _menu

    private val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    private val _resultText = MutableLiveData<String>()
    val resultText: LiveData<String>
        get() = _resultText

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _score.value = score
        _menu.value = menu
        _result.value = result
        _resultText.value = getResult()
    }

    private fun getResult () : String {
        if (_result.value!!) return "${_score.value?.scoreCorrect} ข้อที่ถูกแล้ว"
        return "${_score.value?.scoreInCorrect} ข้อที่ผิดแล้ว"
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }
}