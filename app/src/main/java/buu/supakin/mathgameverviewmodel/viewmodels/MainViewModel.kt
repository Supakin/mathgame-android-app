package buu.supakin.mathgameverviewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverviewmodel.models.Score

class MainViewModel (score : Score = Score()): ViewModel() {
    private val _score = MutableLiveData<Score>()
    val score: LiveData<Score>
        get() = _score

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _score.value = score
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }
}