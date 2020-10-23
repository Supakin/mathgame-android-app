package buu.supakin.mathgameverdatabase.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface PlayerDatabaseDao {
    @Insert
    suspend fun insert(player: Player)

    @Update
    suspend fun update(player: Player)

    @Query("SELECT * from player_table WHERE playerId = :key")
    suspend fun get(key: Long): Player?

    @Query("SELECT * from player_table WHERE name = :name")
    suspend fun getByName(name: String): Player?

    @Query("SELECT * from player_table ORDER BY (score_correct - score_incorrect) DESC")
    fun  getAllPlayerDesc(): LiveData<List<Player>>

    @Query("DELETE from player_table WHERE playerId = :key")
    suspend fun delete(key: Long)
}