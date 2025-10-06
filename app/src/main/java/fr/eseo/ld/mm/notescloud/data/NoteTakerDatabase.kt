package fr.eseo.ld.mm.notescloud.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eseo.ld.mm.notescloud.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(NoteTakerDataConverters::class)
abstract class NoteTakerDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

