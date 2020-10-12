package buu.supakin.mathgameverdatabase.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.models.Score
import buu.supakin.mathgameverdatabase.viewmodels.ResultViewModel
import java.lang.IllegalArgumentException

class ResultViewModelFactory (private val dataSource: PlayerDatabaseDao, private val playerId: Long, private val menu : Int = 0, private val result : Boolean = true): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(dataSource, playerId, menu, result) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}