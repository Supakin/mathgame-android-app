package buu.supakin.mathgameverdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverdatabase.models.Score

class MenuViewModel (score : Score = Score()) : ViewModel() {
    private val _score = MutableLiveData<Score>()
    val score: LiveData<Score>
        get() = _score

    private val _eventNextToPlusMode = MutableLiveData<Boolean>()
    val eventNextToPlusMode: LiveData<Boolean>
        get() = _eventNextToPlusMode

    private val _eventNextToMinusMode = MutableLiveData<Boolean>()
    val eventNextToMinusMode: LiveData<Boolean>
        get() = _eventNextToMinusMode

    private val _eventNextToMultipliedMode = MutableLiveData<Boolean>()
    val eventNextToMultipliedMode: LiveData<Boolean>
        get() = _eventNextToMultipliedMode

    private val _eventNextToDivideMode = MutableLiveData<Boolean>()
    val eventNextToDivideMode: LiveData<Boolean>
        get() = _eventNextToDivideMode

    init {
        _score.value = score
    }

    fun onNextToPlusMode () {
        _eventNextToPlusMode.value = true
    }

    fun onNextToPlusModeComplete () {
        _eventNextToPlusMode.value = false
    }

    fun onNextToMinusMode () {
        _eventNextToMinusMode.value = true
    }

    fun onNextToMinusModeComplete () {
        _eventNextToMinusMode.value = false
    }

    fun onNextToMultipliedMode () {
        _eventNextToMultipliedMode.value = true
    }

    fun onNextToMultipliedModeComplete () {
        _eventNextToMultipliedMode.value = false
    }

    fun onNextToDivideMode () {
        _eventNextToDivideMode.value = true
    }

    fun onNextToDivideModeComplete () {
        _eventNextToDivideMode.value = false
    }
}