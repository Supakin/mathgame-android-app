package buu.supakin.mathgameverdatabase.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao
import buu.supakin.mathgameverdatabase.formatRanking

class RankingViewModel (private val database: PlayerDatabaseDao,
                        application: Application) : AndroidViewModel(application) {
    val playerList = database.getAllPlayerDesc()
//    val playerListString = Transformations.map(playerList) { playerList->
//        formatRanking(playerList, application.resources)

}