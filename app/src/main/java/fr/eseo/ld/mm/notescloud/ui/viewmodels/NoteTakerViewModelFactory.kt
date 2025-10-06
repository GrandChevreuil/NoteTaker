package fr.eseo.ld.mm.notescloud.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import fr.eseo.ld.mm.notescloud.repositories.NoteTakerRepository

class NoteTakerViewModelFactory (
    private val repository : NoteTakerRepository
) : ViewModelProvider.Factory
{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(NoteTakerViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NoteTakerViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }



}