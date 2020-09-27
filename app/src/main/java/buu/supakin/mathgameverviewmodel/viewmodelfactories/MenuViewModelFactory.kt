package buu.supakin.mathgameverviewmodel.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodels.MenuViewModel
import java.lang.IllegalArgumentException

class MenuViewModelFactory (private val score : Score = Score()) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MenuViewModel::class.java)) {
            return MenuViewModel(score) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}