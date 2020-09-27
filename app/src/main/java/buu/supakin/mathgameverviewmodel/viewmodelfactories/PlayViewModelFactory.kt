package buu.supakin.mathgameverviewmodel.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodels.PlayViewModel
import java.lang.IllegalArgumentException

class PlayViewModelFactory (private val score : Score = Score(), private val menu : Int = 0) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayViewModel::class.java)) {
            return PlayViewModel(score, menu) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}