package fr.eseo.ld.mm.notesroom.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import fr.eseo.ld.mm.notesroom.model.Note
import fr.eseo.ld.mm.notesroom.repositories.NoteTakerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class NoteTakerViewModel(private val _repository: NoteTakerRepository) : ViewModel() {
    private val repository: NoteTakerRepository = _repository
    private val _notes = MutableStateFlow<List<Note>>(emptyList())
    val notes : StateFlow<List<Note>> = _notes.asStateFlow()

    private val _note = MutableStateFlow<Note?>(null)
    val note : StateFlow<Note?> = _note.asStateFlow()

    init {
        refreshNotes()
    }

    fun refreshNotes () {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getNotes()
            }
            _notes.value = result
        }
    }

    fun addOrUpdateNote(note : Note){
        viewModelScope.launch (Dispatchers.IO){
            repository.addOrUpdateNote(note)
        }
    }

    fun getNoteById(noteID : String) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                repository.getNoteById(noteID)
            }
            _note.value = result
        }
    }

    fun deleteNote(note : Note){
        viewModelScope.launch{
            withContext(Dispatchers.IO) {
                repository.deleteNote(note)
            }
            refreshNotes()
        }
    }

}