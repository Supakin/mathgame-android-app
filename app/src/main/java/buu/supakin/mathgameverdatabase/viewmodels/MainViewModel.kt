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
import buu.supakin.mathgameverdatabase.validatePlayerNameIsEmpty
import buu.supakin.mathgameverdatabase.validatePlayerNameIsLong

class MainViewModel(private val database: PlayerDatabaseDao) : ViewModel() {
    val name = MutableLiveData<String>()

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    private val _player = MutableLiveData<Player>()
    val player: LiveData<Player>
        get() = _player

    private val _eventNext = MutableLiveData<Boolean>()
    val eventNext: LiveData<Boolean>
        get() = _eventNext

    init {
        _player.value = Player()
        _error.value = ""
        _eventNext.value = false
    }

    fun onNext() {
        viewModelScope.launch {
            if (!validatePlayerNameIsEmpty(name.value)) {
                if (!validatePlayerNameIsLong(name.value.toString())) {
                    if (!hasPlayer()) {
                        var playerTable = PlayerTable()
                        playerTable.name = name.value.toString()
                        insert(playerTable)
                        playerTable = getPlayerByName(name.value.toString())!!
                        _player.value = createPlayer(playerTable)
                    }
                    _eventNext.value = true
                } else {
                    _error.value = "กรุณาระบุชื่อผู้เล่นที่มีความยาวไม่เกิน 8 ตัวอักษร"
                }
            } else {
                _error.value = "กรุณาระบุชื่อผู้เล่นอย่างน้อย 1 ตัวอักษร"
            }
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
        return if (playerTable != null) {
            _player.value = createPlayer(playerTable)
            true
        } else {
            false
        }
    }


}