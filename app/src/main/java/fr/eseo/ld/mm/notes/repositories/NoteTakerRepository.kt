package fr.eseo.ld.mm.notes.repositories

import fr.eseo.ld.mm.notes.model.Note

interface NoteTakerRepository {
    suspend fun getNotes() : List<Note>
    suspend fun addOrUpdateNote(note : Note)
    suspend fun getNoteById(noteId : String) : Note?
    suspend fun deleteNote(note : Note)
}