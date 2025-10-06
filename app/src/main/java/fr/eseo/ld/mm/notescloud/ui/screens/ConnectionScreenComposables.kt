package fr.eseo.ld.mm.notescloud.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import fr.eseo.ld.mm.notescloud.ui.theme.Anthracite
import fr.eseo.ld.mm.notescloud.ui.theme.LightGrey
import fr.eseo.ld.mm.notescloud.ui.theme.White
import fr.eseo.ld.mm.notescloud.viewmodels.AuthenticationViewModel

@Composable
fun ConnectionScreen(
    navController: NavController,
    authenticationViewModel: AuthenticationViewModel
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(horizontal = 16.dp, vertical = 24.dp),
        color = Anthracite
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top
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
                value = email,
                onValueChange = { email = it },
                label = { Text("Email") },
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(8.dp))
            TextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("Password") },
                visualTransformation = PasswordVisualTransformation(),
                modifier = Modifier.fillMaxWidth(),
                colors = textFieldColors
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    authenticationViewModel.login(email, password)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Login")
            }
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                onClick = {
                    authenticationViewModel.signUp(email, password)
                    navController.popBackStack()
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Sign Up")
            }
        }
    }
}
