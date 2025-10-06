package fr.eseo.ld.mm.notescloud.viewmodels

import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import fr.eseo.ld.mm.notescloud.repositories.AuthenticationRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val authenticationRepository: AuthenticationRepository
) : ViewModel() {
    private val _user = MutableStateFlow<FirebaseUser?>(null)
    val user: StateFlow<FirebaseUser?>
        get() = _user.asStateFlow()

    init {
        _user.value = authenticationRepository.getCurrentUser()
        if (_user.value == null) {
            loginAnonymously()
        }
    }

    fun loginAnonymously() {
        authenticationRepository.loginAnonymously().addOnCompleteListener { task ->
            if (task.isSuccessful) {
                _user.value = authenticationRepository.getCurrentUser()
            } else {
                _user.value = null
            }
        }
    }
}
