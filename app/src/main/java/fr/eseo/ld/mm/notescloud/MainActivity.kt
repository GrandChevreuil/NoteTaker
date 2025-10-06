package fr.eseo.ld.mm.notescloud

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import fr.eseo.ld.mm.notescloud.ui.NoteTakerUi
import fr.eseo.ld.mm.notescloud.ui.theme.NoteTakerTheme

@AndroidEntryPoint
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
