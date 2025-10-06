package fr.eseo.ld.mm.notescloud.repositories

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.android.gms.tasks.Task
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(
    private val firebaseAuth: FirebaseAuth
) {
    fun loginAnonymously(): Task<com.google.firebase.auth.AuthResult> =
        firebaseAuth.signInAnonymously()

    fun signUpWithEmail(email: String, password: String): Task<com.google.firebase.auth.AuthResult> =
        firebaseAuth.createUserWithEmailAndPassword(email, password)

    fun loginWithEmail(email: String, password: String): Task<com.google.firebase.auth.AuthResult> =
        firebaseAuth.signInWithEmailAndPassword(email, password)

    fun logout() = firebaseAuth.signOut()

    fun getCurrentUser(): FirebaseUser? = firebaseAuth.currentUser
}

