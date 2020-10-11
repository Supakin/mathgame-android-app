package buu.supakin.mathgameverdatabase.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PlayerTable::class], version = 1, exportSchema = false)
abstract class PlayerDatabase: RoomDatabase() {
    abstract val playerDatabaseDao: PlayerDatabaseDao

    companion object {
        @Volatile
        private var INSTANCE: PlayerDatabase? = null

        fun getInstance(context: Context): PlayerDatabase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance === null) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PlayerDatabase::class.java,
                        "math_game_database")
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}