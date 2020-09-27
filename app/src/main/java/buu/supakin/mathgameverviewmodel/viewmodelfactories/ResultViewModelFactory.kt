package buu.supakin.mathgameverviewmodel.viewmodelfactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import buu.supakin.mathgameverviewmodel.models.Score
import buu.supakin.mathgameverviewmodel.viewmodels.ResultViewModel
import java.lang.IllegalArgumentException

class ResultViewModelFactory (private val score : Score = Score(), private val menu : Int = 0, private val result : Boolean = true): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ResultViewModel::class.java)) {
            return ResultViewModel(score, menu, result) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}