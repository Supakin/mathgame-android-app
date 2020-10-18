package buu.supakin.mathgameverdatabase.viewmodels

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import buu.supakin.mathgameverdatabase.database.PlayerDatabaseDao

class RankingViewModel (private val database: PlayerDatabaseDao,
                        application: Application) : AndroidViewModel(application) {
    val playerList = database.getAllPlayerDesc()
//    val playerListString = Transformations.map(playerList) { playerList->
//        formatRanking(playerList, application.resources)

}