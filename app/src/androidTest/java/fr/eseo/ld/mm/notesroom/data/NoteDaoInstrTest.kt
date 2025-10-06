package fr.eseo.ld.mm.notesroom.data

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import fr.eseo.ld.mm.notesroom.model.Note
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.time.LocalDateTime

@RunWith(AndroidJUnit4::class)
class NoteDaoInstrTest {
    private lateinit var database: NoteTakerDatabase
    private lateinit var noteDao: NoteDao

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext<Context>(),
            NoteTakerDatabase::class.java
        ).allowMainThreadQueries().build()
        noteDao = database.noteDao()
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testInsertNote() = runBlocking {
        val now = LocalDateTime.now().withNano(0)
        val note = Note(
            id = "123",
            author = "tester",
            title = "Test Me",
            body = "Instrumented Testing",
            creationDate = now,
            modificationDate = now
        )
        noteDao.insert(note)
        val fetchNote = noteDao.getNoteById("123")
        assertEquals(note, fetchNote)
    }
}
