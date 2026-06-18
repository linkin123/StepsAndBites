package com.linkin.stepsandbites.auth.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.linkin.stepsandbites.R

private val VitaGreen = Color(0xFF22A861)
private val VitaGreenMid = Color(0xFFD6F2E5)
private val VitaGreenLight = Color(0xFFF0FAF5)
private val TextPrimary = Color(0xFF1A1A1A)
private val TextSecondary = Color(0xFF777777)
private val CardBorder = Color(0xFFDDDDDD)

@Composable
actual fun LoginScreen(onLoginSuccess: () -> Unit) {
    val context = LocalContext.current
    val viewModel: AuthViewModel = viewModel()
    val authState by viewModel.authState.collectAsState()
    val webClientId = stringResource(R.string.default_web_client_id)

    LaunchedEffect(authState) {
        if (authState is AuthState.Success) {
            onLoginSuccess()
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(VitaGreenLight),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .background(VitaGreenMid),
                contentAlignment = Alignment.Center
            ) {
                Text("🥗", fontSize = 44.sp)
            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = "Steps & Bites",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )

            Spacer(Modifier.height(8.dp))

            Text(
                text = "Come bien, vive mejor",
                fontSize = 14.sp,
                color = TextSecondary
            )

            Spacer(Modifier.height(48.dp))

            if (authState is AuthState.Loading) {
                CircularProgressIndicator(color = VitaGreen)
            } else {
                OutlinedButton(
                    onClick = { viewModel.signInWithGoogle(context, webClientId) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(14.dp),
                    border = BorderStroke(1.dp, CardBorder),
                    colors = ButtonDefaults.outlinedButtonColors(containerColor = Color.White)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_google),
                        contentDescription = null,
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )
                    Spacer(Modifier.width(10.dp))
                    Text(
                        text = "Continuar con Google",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = TextPrimary
                    )
                }
            }

            AnimatedVisibility(visible = authState is AuthState.Error) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Spacer(Modifier.height(12.dp))
                    Text(
                        text = (authState as? AuthState.Error)?.message ?: "",
                        color = Color(0xFFD32F2F),
                        fontSize = 13.sp
                    )
                }
            }
        }
    }
}
