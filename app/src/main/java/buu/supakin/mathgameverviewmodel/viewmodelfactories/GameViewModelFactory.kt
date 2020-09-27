package buu.supakin.mathgameverviewmodel.viewmodelfactories;

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverviewmodel.viewmodels.GameViewModel
import java.lang.IllegalArgumentException

class GameViewModelFactory( private val finalCorrectScore : Int = 0,
                            private val finalInCorrectScore: Int = 0,
                            private val finalMenu: Int = 0,
                            private val finalResult: Boolean = true) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            return GameViewModel(finalCorrectScore, finalInCorrectScore, finalMenu, finalResult) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
