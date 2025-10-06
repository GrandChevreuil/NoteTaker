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
import fr.eseo.ld.mm.notescloud.ui.screens.ConnectionScreen
import fr.eseo.ld.mm.notescloud.ui.screens.DetailsScreen
import fr.eseo.ld.mm.notescloud.ui.screens.SummaryScreen
import fr.eseo.ld.mm.notescloud.ui.viewmodels.NoteTakerViewModel
import fr.eseo.ld.mm.notescloud.ui.viewmodels.NoteTakerViewModelFactory
import fr.eseo.ld.mm.notescloud.viewmodels.AuthenticationViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.LaunchedEffect

@Composable
fun NoteTakerUi() {
    val navController = rememberNavController()
    val application = LocalContext.current.applicationContext as Application
    val viewModel : NoteTakerViewModel = viewModel(
        factory = NoteTakerViewModelFactory(
            NoteTakerRepositoryRoomImpl(application)
        )
    )
    val authenticationViewModel: AuthenticationViewModel = hiltViewModel()

    NavHost(
        navController = navController,
        startDestination = "start"
    ) {
        composable("start") {
            val user by authenticationViewModel.user.collectAsState()
            LaunchedEffect(user) {
                if (user == null) {
                    authenticationViewModel.loginAnonymously()
                } else {
                    navController.navigate(NoteTakerScreens.SUMMARY_SCREEN.name) {
                        popUpTo("start") { inclusive = true }
                    }
                }
            }
        }
        composable(NoteTakerScreens.SUMMARY_SCREEN.name) {
            SummaryScreen(viewModel = viewModel, navController = navController, authenticationViewModel = authenticationViewModel)
        }
        composable(
            NoteTakerScreens.DETAILS_SCREEN.name + "/{noteId}",
            arguments = listOf(
                navArgument("noteId") {
                    type = NavType.StringType
                }
            )
        ) { backStackEntry ->
            val noteId = backStackEntry.arguments?.getString("noteId") ?: "NEW"
            viewModel.getNoteById(noteId)
            DetailsScreen(navController, noteId, viewModel, authenticationViewModel)
        }
        composable(NoteTakerScreens.CONNECTION_SCREEN.name) {
            ConnectionScreen(navController, authenticationViewModel)
        }
    }
}
