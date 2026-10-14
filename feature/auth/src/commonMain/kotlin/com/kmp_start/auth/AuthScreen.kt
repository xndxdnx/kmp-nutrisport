package com.kmp_start.auth

import ContentWithMessageBar
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.kmp_start.shared.Alpha
import com.example.kmp_start.shared.BebasNeueFont
import com.example.kmp_start.shared.FontSize
import com.example.kmp_start.shared.RobotoCondensedFont
import com.example.kmp_start.shared.Surface
import com.example.kmp_start.shared.TextPrimary
import com.example.kmp_start.shared.TextSecondary
import com.kmp_start.auth.component.GoogleButton
import com.mmk.kmpauth.firebase.google.GoogleButtonUiContainerFirebase
import rememberMessageBarState

@Composable
fun AuthScreen() {
    
    val messageBarState = rememberMessageBarState()
    
    var loadingState by remember { mutableStateOf(false) }
    
    Scaffold(

    ) { paddingValues ->
        ContentWithMessageBar(
            contentBackgroundColor = Surface,
            modifier = Modifier
                .padding(
                    top =  paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding()
                ),
            messageBarState = messageBarState,
            errorMaxLines = 2
        ) {
            Column (
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp)
            ){ 
                Column(
                    modifier = Modifier
                        .weight(1f),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth(),
                        text = "NUTRISPORT",
                        textAlign = TextAlign.Center,
                        fontFamily = BebasNeueFont(),
                        fontSize = FontSize.EXTRA_LARGE,
                        color = TextSecondary
                    )

                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .alpha(Alpha.HALF),
                        text = "SignIn to continue ",
                        textAlign = TextAlign.Center,
                        fontSize = FontSize.EXTRA_REGULAR,
                        color = TextPrimary
                    )
                    
                }

                GoogleButtonUiContainerFirebase(
                    linkAccount = false,
                    onResult = { result ->
                        result.onSuccess { user -> 
                            messageBarState.addSuccess("Authentication successful!")
                            loadingState = false    
                        }.onFailure { exception ->
                            val message = exception.message.orEmpty()
                            if (
                                message.contains("canceled", ignoreCase = true) ||
                                message.contains("cancelled", ignoreCase = true) ||
                                message.contains("Idtoken is null", ignoreCase = true)
                            ) {
                                // User dismissed the Google sheet — not an app error.
                            } else if (message.contains("A network error", ignoreCase = true)) {
                                messageBarState.addError("Internet connection unavailable")
                            } else {
                                messageBarState.addError(message.ifBlank { "Unknown" })
                            }
                            
                            loadingState = false
                        }
                    }
                ){
                    GoogleButton(
                        loading = loadingState,
                        onClick = {
                            loadingState = true
                            this@GoogleButtonUiContainerFirebase.onClick()
                        }
                    )
                }
                
                
                
            }
        }
    }
}