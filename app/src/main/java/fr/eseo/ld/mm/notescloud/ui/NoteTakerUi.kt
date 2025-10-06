package fr.eseo.ld.mm.notescloud.ui

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.eseo.ld.mm.notescloud.repositories.NoteTakerRepositoryRoomImpl
import fr.eseo.ld.mm.notescloud.ui.navigation.NoteTakerScreens
import fr.eseo.ld.mm.notescloud.ui.screens.DetailsScreen
import fr.eseo.ld.mm.notescloud.ui.screens.SummaryScreen
import fr.eseo.ld.mm.notescloud.ui.viewmodels.NoteTakerViewModel
import fr.eseo.ld.mm.notescloud.ui.viewmodels.NoteTakerViewModelFactory

@Composable
fun NoteTakerUi() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as Application
    val viewModel : NoteTakerViewModel = viewModel(
        factory = NoteTakerViewModelFactory(
            NoteTakerRepositoryRoomImpl(application)
        )
    )

    NavHost(
        navController = navController,
        startDestination = NoteTakerScreens.SUMMARY_SCREEN.name
    ) {
        composable(NoteTakerScreens.SUMMARY_SCREEN.name) {
            SummaryScreen(viewModel = viewModel, navController = navController)

        }

        composable (
            NoteTakerScreens.DETAILS_SCREEN.name+"/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                }
            )
        ) {
                backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: "NEW"
            viewModel.getNoteById(noteId)
            DetailsScreen(navController, noteId, viewModel)
        }

    }
}
