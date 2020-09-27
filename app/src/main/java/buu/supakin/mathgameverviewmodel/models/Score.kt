package buu.supakin.mathgameverviewmodel.models

class Score (scoreCorrect: Int = 0, scoreInCorrect: Int = 0) {
    var scoreCorrect = scoreCorrect
    var scoreInCorrect = scoreInCorrect

    fun onCorrect () {
        this.scoreCorrect++
    }

    fun onInCorrect () {
        this.scoreInCorrect++
    }
}