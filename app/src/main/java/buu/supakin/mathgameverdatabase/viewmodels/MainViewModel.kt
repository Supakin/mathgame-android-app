package buu.supakin.mathgameverdatabase.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.PlayerTable
import buu.supakin.mathgameverdatabase.models.Player
import kotlinx.coroutines.launch
import buu.supakin.mathgameverdatabase.createPlayer

class MainViewModel(private val database: PlayerDatabaseDao) : ViewModel() {
    val name = MutableLiveData<String>()

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player>
        get() = _player

    var playerId: Long = -1

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _player.value = Player()
    }

    fun onNext() {
        viewModelScope.launch {
            if (!hasPlayer()) {
                var playerTable = PlayerTable()
                playerTable.name = name.value.toString()
                insert(playerTable)
                playerTable = getPlayerByName(name.value.toString())!!
                Log.i("MainViewModel", playerTable.toString())
                _player.value = createPlayer(playerTable)
            }
            _eventNext.value = true
        }
    }

    fun onNextComplete() {
        _eventNext.value = false
    }

    private suspend fun insert(player: PlayerTable) {
        database.insert(player)
    }

    private suspend fun getPlayerByName(name: String): PlayerTable? {
        return database.getByName(name)
    }

    private suspend fun hasPlayer(): Boolean {
        val playerTable = getPlayerByName(name.value.toString())
        Log.i("MainViewModel1", playerTable.toString())
        return if (playerTable != null) {
            _player.value = createPlayer(playerTable)
            true
        } else {
            false
        }
    }


}