package fr.eseo.ld.mm.notescloud.ui.screens

import androidx.compose.runtime.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import fr.eseo.ld.mm.notescloud.ui.theme.Anthracite
import fr.eseo.ld.mm.notescloud.ui.theme.LightGrey
import fr.eseo.ld.mm.notescloud.ui.theme.White
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.eseo.ld.mm.notescloud.model.Note
import fr.eseo.ld.mm.notescloud.ui.viewmodels.NoteTakerViewModel
import fr.eseo.ld.mm.notescloud.viewmodels.AuthenticationViewModel
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import fr.eseo.ld.mm.notescloud.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavController,
    noteId: String,
    viewModel: NoteTakerViewModel,
    authenticationViewModel: AuthenticationViewModel
) {
    val note by viewModel.note.collectAsState()
    var title by remember { mutableStateOf(note?.title ?: "") }
    var body by remember { mutableStateOf(note?.body ?: "") }
    var author by remember { mutableStateOf(note?.author ?: "") }
    var id by remember { mutableStateOf(note?.id) }
    val date = LocalDateTime.now()
    var editable by remember { mutableStateOf(true) }

    LaunchedEffect(noteId, note) {
        if (noteId == "NEW") {
            id = null
            title = ""
            body = ""
            author = authenticationViewModel.user.value?.email ?: "??"
            editable = true
        } else {
            viewModel.getNoteById(noteId)
            note?.let { note ->
                id = note.id
                title = note.title
                body = note.body
                author = note.author
                editable = note.author == authenticationViewModel.user.value?.email
            }
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(),
        color = MaterialTheme.colorScheme.background
    ) {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = {
                        Text(
                            text = stringResource(R.string.app_name)
                        )
                    },
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                navController.popBackStack()
                            }
                        ) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                contentDescription = stringResource(R.string.back)
                            )
                        }
                    },
                    actions = {
                        IconButton(
                            onClick = {
                                val now = LocalDateTime.now()
                                val newNote = Note.create(
                                    id = id ?: "",
                                    title = title,
                                    body = body,
                                    author = authenticationViewModel.user.value?.email ?: author,
                                    creationDate = if (note != null) note.creationDateLocal else now,
                                    modificationDate = now
                                )
                                viewModel.addOrUpdateNote(newNote)
                                navController.navigateUp()
                            },
                            enabled = editable
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Done,
                                contentDescription = stringResource(R.string.save_note),
                                tint = Anthracite
                            )
                        }
                    }
                )
            },
            content = { innerPadding ->
                DetailsScreenNoteCard(
                    title = title,
                    body = body,
                    author = author,
                    creationDate = note?.creationDateLocal ?: date,
                    modificationDate = note?.modificationDateLocal ?: date,
                    onBodyChange = { body = it },
                    onTitleChange = { title = it },
                    modifier = Modifier.padding(innerPadding),
                    editable = editable
                )
            }
        )
    }
}

@Composable
private fun DetailsScreenNoteCard(
    title: String,
    body: String,
    author: String,
    creationDate: LocalDateTime,
    modificationDate: LocalDateTime,
    modifier: Modifier = Modifier,
    onTitleChange: (String) -> Unit,
    onBodyChange: (String) -> Unit,
    editable: Boolean
) {

    Card(
        modifier = modifier
            .fillMaxSize(),
        elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
        colors = CardDefaults.cardColors(
            containerColor = Anthracite,
            contentColor = White
        )
    ) {
        val textFieldColors = TextFieldDefaults.colors(
            focusedContainerColor = Anthracite,
            unfocusedContainerColor = Anthracite,
            disabledContainerColor = Anthracite,
            focusedTextColor = White,
            unfocusedTextColor = White,
            disabledTextColor = LightGrey,
            cursorColor = White,
            focusedLabelColor = LightGrey,
            unfocusedLabelColor = LightGrey,
            disabledLabelColor = LightGrey,
            focusedIndicatorColor = LightGrey,
            unfocusedIndicatorColor = LightGrey,
            disabledIndicatorColor = LightGrey,
            focusedSupportingTextColor = LightGrey,
            unfocusedSupportingTextColor = LightGrey
        )
        TextField(
            value = title,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge.copy(
                color = White,
                fontSize = MaterialTheme.typography.titleLarge.fontSize * 1.2f
            ),
            onValueChange = onTitleChange,
            label = {
                Text(
                    text = stringResource(R.string.title_label),
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth(),
            readOnly = !editable,
            colors = textFieldColors
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )
        TextField(
            value = body,
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = White,
                fontSize = MaterialTheme.typography.bodyLarge.fontSize * 1.2f
            ),
            onValueChange = onBodyChange,
            label = {
                Text(
                    text = stringResource(R.string.body_label),
                    color = White
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            readOnly = !editable,
            colors = textFieldColors
        )
        Spacer(
            modifier = Modifier.height(8.dp)
        )

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.created_by),
                color = White
            )
            Text(
                text = author,
                color = White
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.creation_date),
                color = White
            )
            Text(
                text = displayDate(creationDate, true),
                color = White
            )
        }
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text(
                text = stringResource(R.string.modification_date),
                color = White
            )
            Text(
                text = displayDate(modificationDate, true),
                color = White
            )
        }

    }

}

private fun displayDate(date : LocalDateTime, since : Boolean) : String {
    val formatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm")
    var display = formatter.format(date)
    if(since){
        val daysAgo = ChronoUnit.DAYS.between(date.toLocalDate(), LocalDate.now())
        display = display.plus(" (${daysAgo} days ago)")
    }
    return display
}

private fun createId(date:LocalDateTime) : String {
    val formatter = DateTimeFormatter.ofPattern("yyMMddhhmmss")
    return formatter.format(date)
}