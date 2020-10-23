package buu.supakin.mathgameverdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.Player
import kotlinx.coroutines.launch

class MenuViewModel (private val database: PlayerDatabaseDao, playerId: Long) : ViewModel() {
    private val playerId: Long = playerId

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player>
        get() = _player

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
        viewModelScope.launch {
            _player.value = getPlayer(playerId)
        }
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

    private suspend fun getPlayer(id: Long): Player? {
        return database.get(id)
    }

}