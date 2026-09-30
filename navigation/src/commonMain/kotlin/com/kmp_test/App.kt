package com.kmp_test

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.example.kmp_start.shared.Constants.WEB_CLIENT_ID
import com.kmp_test.navigation.SetupNavGraph
import com.mmk.kmpauth.google.GoogleAuthCredentials
import com.mmk.kmpauth.google.GoogleAuthProvider

@Composable
fun App() {

    var appReady by remember { mutableStateOf(false) }


    MaterialTheme {


        LaunchedEffect(Unit) {
            GoogleAuthProvider.create(
                credentials = GoogleAuthCredentials(serverId = WEB_CLIENT_ID)
            )
            appReady = true

        }

        AnimatedVisibility(
            modifier = Modifier
                .fillMaxSize(),
            visible = appReady
        ) {
            SetupNavGraph()

        }

    }
}