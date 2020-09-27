package buu.supakin.mathgameverviewmodel.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class GameViewModel(finalCorrectScore : Int = 0,
                    finalInCorrectScore: Int = 0,
                    finalMenu: Int = 0,
                    finalResult: Boolean = true) : ViewModel() {

    private  val _correctScore = MutableLiveData<Int>()
    val correctScore: LiveData<Int>
        get() = _correctScore

    private val _inCorrectScore = MutableLiveData<Int>()
    val inCorrectScore: LiveData<Int>
        get() = _inCorrectScore

    private  val _menu = MutableLiveData<Int>()
    val menu: LiveData<Int>
        get() = _menu


    private  val _result = MutableLiveData<Boolean>()
    val result: LiveData<Boolean>
        get() = _result

    init {
        _correctScore.value = finalCorrectScore
        _inCorrectScore.value = finalInCorrectScore
        _menu.value = finalMenu
        _result.value = finalResult
    }

    fun onCorrect () {
        _correctScore.value = (_correctScore.value)?.plus(1)
        _result.value = true
    }

    fun onInCorrect () {
        _inCorrectScore.value = (_inCorrectScore.value)?.plus(1)
        _result.value = false
    }
}