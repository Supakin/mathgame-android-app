package buu.supakin.mathgameverviewmodel.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodels.MainViewModel
import java.lang.IllegalArgumentException

class MainViewModelFactory (private val score : Score = Score()) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(score) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}