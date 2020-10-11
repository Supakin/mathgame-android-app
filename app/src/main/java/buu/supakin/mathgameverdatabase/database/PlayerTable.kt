package buu.supakin.mathgameverdatabase.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "player_table")
data class PlayerTable (
    @PrimaryKey(autoGenerate = true)
    var playerId: Long = 0L,

    @ColumnInfo(name = "name")
    var name: String = "",

    @ColumnInfo(name = "score_correct")
    var scoreCorrect: Int = 0,

    @ColumnInfo(name = "score_incorrect")
    var scoreInCorrect: Int = 0
)

