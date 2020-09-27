package buu.supakin.mathgameverviewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverviewmodel.models.Score

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

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _score.value = score
        _menu.value = menu
        _result.value = result
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }
}