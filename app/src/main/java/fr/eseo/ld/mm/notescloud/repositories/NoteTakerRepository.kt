package fr.eseo.ld.mm.notescloud.repositories

import fr.eseo.ld.mm.notescloud.model.Note

interface NoteTakerRepository {
    suspend fun getNotes() : List<Note>
    suspend fun addOrUpdateNote(note : Note)
    suspend fun getNoteById(noteId : String) : Note?
    suspend fun deleteNote(note : Note)
}