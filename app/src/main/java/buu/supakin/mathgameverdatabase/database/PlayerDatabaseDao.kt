package buu.supakin.mathgameverdatabase.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDatabaseDao {
    @Insert
    suspend fun insert(playerTable: PlayerTable)

    @Update
    suspend fun update(playerTable: PlayerTable)

    @Query("SELECT * from player_table WHERE playerId = :key")
    suspend fun get(key: Long): PlayerTable?

    @Query("SELECT * from player_table WHERE name = :name")
    suspend fun getByName(name: String): PlayerTable?

    @Query("SELECT * from player_table ORDER BY score_correct DESC")
    suspend fun  getAllPlayerDesc(): List<PlayerTable>

    @Query("DELETE from player_table WHERE playerId = :key")
    suspend fun delete(key: Long)
}