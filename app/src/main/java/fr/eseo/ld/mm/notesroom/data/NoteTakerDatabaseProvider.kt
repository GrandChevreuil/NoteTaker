package fr.eseo.ld.mm.notesroom.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

object NoteTakerDatabaseProvider {
    @Volatile
    private var INSTANCE: NoteTakerDatabase? = null

    fun getDatabase(context: Context): NoteTakerDatabase {
        return INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                NoteTakerDatabase::class.java,
                "note-database"
            ).build()
            INSTANCE = instance
            instance
        }
    }
}

