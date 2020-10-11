package buu.supakin.mathgameverdatabase.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodels.MenuViewModel
import java.lang.IllegalArgumentException

class MenuViewModelFactory (private val dataSource: PlayerDatabaseDao, private val playerId: Long) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(dataSource, playerId) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}