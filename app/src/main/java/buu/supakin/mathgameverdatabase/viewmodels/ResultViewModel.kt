package buu.supakin.mathgameverdatabase.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import buu.supakin.mathgameverdatabase.createPlayer
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.database.PlayerTable
import buu.supakin.mathgameverdatabase.models.Player
import buu.supakin.mathgameverdatabase.models.Score
import kotlinx.coroutines.launch

class ResultViewModel (private val database: PlayerDatabaseDao, playerId: Long, menu: Int = 0, result: Boolean = true): ViewModel() {
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
        _menu.value = menu
        _result.value = result
        viewModelScope.launch {
            initPlayer()
            setResultText()
        }
    }

    private fun setResultText () {
        if (_result.value!!) _resultText.value = "${_player.value!!.getScoreCorrect()} ข้อที่ถูกแล้ว"
        else _resultText.value = "${_player.value!!.getScoreInCorrect()} ข้อที่ผิดแล้ว"
    }

    fun onNext () {
        _eventNext.value = true
    }

    fun onNextComplete () {
        _eventNext.value = false
    }

    private suspend fun getPlayer(id: Long): PlayerTable? {
        return database.get(id)
    }

    private suspend fun initPlayer () {
        viewModelScope.launch {
            val playerTable = getPlayer(playerId)
            _player.value = playerTable?.let { createPlayer(it) }
        }
    }
}