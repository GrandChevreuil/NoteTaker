package fr.eseo.ld.mm.notescloud.hiltmodules

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import fr.eseo.ld.mm.notescloud.repositories.NoteTakerRepository
import fr.eseo.ld.mm.notescloud.repositories.NoteTakerRepositoryFirestoreImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    @Provides
    @Singleton
    fun provideFirebaseAuthentication(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun provideFirebaseFirestore(): FirebaseFirestore = FirebaseFirestore.getInstance()

    @Provides
    @Singleton
    fun provideNoteTakerRepository(
        repository: NoteTakerRepositoryFirestoreImpl
    ): NoteTakerRepository = repository
}
