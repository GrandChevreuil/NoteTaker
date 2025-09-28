package fr.eseo.ld.mm.notes

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.eseo.ld.mm.notes.ui.NoteTakerUi
import fr.eseo.ld.mm.notes.ui.screens.NotesComposePreview
import fr.eseo.ld.mm.notes.ui.theme.NoteTakerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("lifecycle", "onCreate Started")
        enableEdgeToEdge()
        setContent {
            NoteTakerTheme {
                NoteTakerUi()
            }
        }
    }
}
