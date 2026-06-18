package com.linkin.stepsandbites.auth.presentation

import androidx.compose.runtime.Composable

@Composable
expect fun LoginScreen(onLoginSuccess: () -> Unit)
