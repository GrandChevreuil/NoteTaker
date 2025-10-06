package fr.eseo.ld.mm.notesroom.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import fr.eseo.ld.mm.notesroom.model.Note

@Database(
    entities = [Note::class],
    version = 1
)
@TypeConverters(NoteTakerDataConverters::class)
abstract class NoteTakerDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}

