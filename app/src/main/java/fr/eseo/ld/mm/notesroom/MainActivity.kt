package fr.eseo.ld.mm.notesroom

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import fr.eseo.ld.mm.notesroom.ui.NoteTakerUi
import fr.eseo.ld.mm.notesroom.ui.theme.NoteTakerTheme

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
