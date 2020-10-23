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
import buu.supakin.mathgameverdatabase.validatePlayerNameIsEmpty
import buu.supakin.mathgameverdatabase.validatePlayerNameIsLong

class MainViewModel(private val database: PlayerDatabaseDao, private val application: Application) : ViewModel() {
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
                        var newPlayer = Player()
                        newPlayer.setName(name.value.toString())
                        insert(newPlayer)
                        newPlayer = getPlayerByName(name.value.toString())!!
                        _player.value = newPlayer
                    }
                    _eventNext.value = true
                } else {
                    //_error.value = "กรุณาระบุชื่อผู้เล่นที่มีความยาวไม่เกิน 8 ตัวอักษร"
                    _error.value = application.getString(R.string.error_is_long)
                }
            } else {
                //_error.value = "กรุณาระบุชื่อผู้เล่นอย่างน้อย 1 ตัวอักษร"
                _error.value = application.getString(R.string.error_is_empty)
            }
        }
    }

    fun onNextComplete() {
        _eventNext.value = false
    }

    private suspend fun insert(player: Player) {
        database.insert(player)
    }

    private suspend fun getPlayerByName(name: String): Player? {
        return database.getByName(name)
    }

    private suspend fun hasPlayer(): Boolean {
        val player = getPlayerByName(name.value.toString())
        return if (player != null) {
            _player.value = player
            true
        } else {
            false
        }
    }


}