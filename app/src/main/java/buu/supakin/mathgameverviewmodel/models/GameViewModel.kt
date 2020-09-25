package buu.supakin.mathgameverviewmodel.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(finalCorrectScore : Int = 0, finalIncorrectScore: Int = 0, finalMenu: Int = 0) : ViewModel() {
    private  val _correctScore = MutableLiveData<Int>()
    val correctScore: LiveData<Int>
        get() = _correctScore

    private val _incorrectScore = MutableLiveData<Int>()
    val incorrectScore: LiveData<Int>
        get() = _incorrectScore

    private  val _menu = MutableLiveData<Int>()
    val menu: LiveData<Int>
        get() = _menu

    init {
        _correctScore.value = finalCorrectScore
        _incorrectScore.value = finalIncorrectScore
        _menu.value = finalMenu
    }

    fun onCorrect () {
        _correctScore.value = (_correctScore.value)?.plus(1)
    }

    fun onIncorrect () {
        _incorrectScore.value = (_incorrectScore.value)?.plus(1)
    }
}