package buu.supakin.mathgameverdatabase.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import buu.supakin.mathgameverdatabase.R
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.Player
import kotlinx.coroutines.launch

class ResultViewModel (private val database: PlayerDatabaseDao,
                       private val application: Application,
                       playerId: Long,
                       menu: Int = 0,
                       result: Boolean = true): ViewModel() {
    private val playerId: Long = playerId
    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player>
        get() = _player

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
        viewModelScope.launch {
            _menu.value = menu
            _result.value = result
            _player.value = getPlayer(playerId)
            setResultText()
        }

    }

    private fun setResultText () {
        if (_result.value!!) _resultText.value = application.getString(R.string.total_correct, _player.value!!.getScoreCorrect())
        else _resultText.value = application.getString(R.string.total_incorrect, _player.value!!.getScoreInCorrect())
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }

    private suspend fun getPlayer(id: Long): Player? {
        return database.get(id)
    }
}