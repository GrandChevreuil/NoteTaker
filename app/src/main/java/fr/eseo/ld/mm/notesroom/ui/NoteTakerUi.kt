package fr.eseo.ld.mm.notesroom.ui

import fr.eseo.ld.mm.notesroom.ui.screens.*
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import fr.eseo.ld.mm.notesroom.repositories.NoteTakerRepositoryListImpl
import fr.eseo.ld.mm.notesroom.ui.navigation.NoteTakerScreens
import fr.eseo.ld.mm.notesroom.ui.viewmodels.NoteTakerViewModel
import fr.eseo.ld.mm.notesroom.ui.viewmodels.NoteTakerViewModelFactory

@Composable
fun NoteTakerUi() {
    val navController = rememberNavController()
    val viewModel : NoteTakerViewModel = viewModel(
        factory = NoteTakerViewModelFactory(
            NoteTakerRepositoryListImpl()
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
