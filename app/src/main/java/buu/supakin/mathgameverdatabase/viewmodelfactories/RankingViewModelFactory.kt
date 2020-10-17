package buu.supakin.mathgameverdatabase.viewmodelfactories

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.viewmodels.RankingViewModel
import buu.supakin.mathgameverdatabase.viewmodels.ResultViewModel
import java.lang.IllegalArgumentException

class RankingViewModelFactory (private val dataSource: PlayerDatabaseDao,
                               private val application: Application): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RankingViewModel::class.java)) {
            return RankingViewModel(dataSource, application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}