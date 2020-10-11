package buu.supakin.mathgameverdatabase.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodels.PlayViewModel
import java.lang.IllegalArgumentException


class PlayViewModelFactory (private val dataSource: PlayerDatabaseDao, private val playerId: Long,private val menu : Int = 0) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayViewModel::class.java)) {
            return PlayViewModel(dataSource, playerId, menu) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}