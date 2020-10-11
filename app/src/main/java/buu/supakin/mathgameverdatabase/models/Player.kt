package buu.supakin.mathgameverdatabase.models

class Player (playerId: Long = -1,
              name: String = "",
              scoreCorrect: Int = 0,
              scoreInCorrect: Int = 0) {

    private var playerId: Long = playerId
    private var name: String = name
    private var scoreCorrect: Int = scoreCorrect
    private var scoreInCorrect: Int = scoreInCorrect

    fun setPlayerId (id: Long) {
        this.playerId = id
    }

    fun getPlayerId () = this.playerId

    fun setName (name: String) {
        this.name = name
    }

    fun getName () = this.name

    fun setScoreCorrect (scoreCorrect: Int) {
        this.scoreCorrect = scoreCorrect
    }

    fun getScoreCorrect () = this.scoreCorrect

    fun setScoreInCorrect (scoreInCorrect: Int) {
        this.scoreInCorrect = scoreInCorrect
    }

    fun getScoreInCorrect () = this.scoreInCorrect
}