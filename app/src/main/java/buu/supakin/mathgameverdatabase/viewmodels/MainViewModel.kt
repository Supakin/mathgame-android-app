package buu.supakin.mathgameverdatabase.viewmodels

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
    private val _name = MutableLiveData<String>()
    val name: LiveData<String>
        get() = _name

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
                playerTable.name = _name.value.toString()
                insert(playerTable)
                playerTable = getPlayerByName(_name.value.toString())!!
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
        val playerTable = getPlayerByName(_name.value.toString())
        return if (playerTable != null) {
            _player.value = createPlayer(playerTable)
            true
        } else {
            false
        }
    }


}