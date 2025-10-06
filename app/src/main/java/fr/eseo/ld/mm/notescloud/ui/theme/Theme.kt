package fr.eseo.ld.mm.notescloud.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = DarkOrange,
    secondary = LightGrey,
    tertiary = White,
    background = Anthracite,
    surface = LightGrey,
    onPrimary = White,
    onSecondary = Anthracite,
    onBackground = White,
    onSurface = Anthracite
)

private val LightColorScheme = lightColorScheme(
    primary = DarkOrange,
    secondary = LightGrey,
    tertiary = White,
    background = Anthracite,
    surface = LightGrey,
    onPrimary = White,
    onSecondary = Anthracite,
    onBackground = White,
    onSurface = Anthracite
)

@Composable
fun NoteTakerTheme(
    darkTheme: Boolean = true, // Forcer le thème sombre
    dynamicColor: Boolean = false, // Désactiver les couleurs dynamiques
    content: @Composable () -> Unit
) {
    // Utiliser le thème sombre personnalisé
    val colorScheme = DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}