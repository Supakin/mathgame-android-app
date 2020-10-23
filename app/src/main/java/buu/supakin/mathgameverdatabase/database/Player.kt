package buu.supakin.mathgameverdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class Player (
    @PrimaryKey(autoGenerate = true)
    private var playerId: Long = 0L,

    @ColumnInfo(name = "name")
    private var name: String = "",

    @ColumnInfo(name = "score_correct")
    private var scoreCorrect: Int = 0,

    @ColumnInfo(name = "score_incorrect")
    private var scoreInCorrect: Int = 0
) {
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

    fun onCorrect () {
        this.scoreCorrect++
    }

    fun onInCorrect () {
        this.scoreInCorrect++
    }
}

