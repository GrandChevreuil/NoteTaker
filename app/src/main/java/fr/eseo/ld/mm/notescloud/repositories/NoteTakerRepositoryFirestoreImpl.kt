package fr.eseo.ld.mm.notescloud.repositories

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.CollectionReference
import fr.eseo.ld.mm.notescloud.model.Note
import kotlinx.coroutines.tasks.await
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NoteTakerRepositoryFirestoreImpl @Inject constructor(
    private val fireStore: FirebaseFirestore
) : NoteTakerRepository {
    private val notesCollection: CollectionReference = fireStore.collection("notes")

    override suspend fun addOrUpdateNote(note: Note) {
        if (note.id.isBlank()) {
            val document = notesCollection.document()
            document.set(note.copy(id = document.id)).await()
        } else {
            notesCollection.document(note.id).set(note).await()
        }
    }

    override suspend fun deleteNote(note: Note) {
        notesCollection.document(note.id).delete().await()
    }

    override suspend fun getNotes(): List<Note> {
        return try {
            val result = notesCollection.get().await()
            result.documents.mapNotNull { document ->
                document.toObject(Note::class.java)
            }
        } catch (e: Exception) {
            emptyList()
        }
    }

    override suspend fun getNoteById(noteId: String): Note? {
        return try {
            val document = notesCollection.document(noteId).get().await()
            document.toObject(Note::class.java)
        } catch (e: Exception) {
            null
        }
    }
}

