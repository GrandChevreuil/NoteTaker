package fr.eseo.ld.mm.notescloud.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.eseo.ld.mm.notescloud.model.Note
import fr.eseo.ld.mm.notescloud.repositories.NoteTakerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class NoteTakerViewModel @Inject constructor(
    private val repository: NoteTakerRepository
) : ViewModel() {
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
            refreshNotes()
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