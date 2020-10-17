package buu.supakin.mathgameverdatabase.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.formatRanking
import buu.supakin.mathgameverdatabase.models.Player

class RankingViewModel (private val database: PlayerDatabaseDao,
                        application: Application) : ViewModel() {
    val playerList = database.getAllPlayerDesc()
    val playerListString = Transformations.map(playerList) { playerList->
        formatRanking(playerList, application.resources)

    }
}