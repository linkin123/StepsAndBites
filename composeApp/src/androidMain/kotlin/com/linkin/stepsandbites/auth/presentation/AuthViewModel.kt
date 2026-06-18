package com.linkin.stepsandbites.auth.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.auth.FirebaseUser
import com.linkin.stepsandbites.auth.data.AuthRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

sealed class AuthState {
    object Idle : AuthState()
    object Loading : AuthState()
    data class Success(val user: FirebaseUser) : AuthState()
    data class Error(val message: String) : AuthState()
}

class AuthViewModel(
    private val repository: AuthRepository = AuthRepository()
) : ViewModel() {

    private val _authState = MutableStateFlow<AuthState>(AuthState.Idle)
    val authState: StateFlow<AuthState> = _authState.asStateFlow()

    fun signInWithGoogle(context: Context, webClientId: String) {
        viewModelScope.launch {
            _authState.value = AuthState.Loading
            repository.signInWithGoogle(context, webClientId)
                .onSuccess { user -> _authState.value = AuthState.Success(user) }
                .onFailure { e -> _authState.value = AuthState.Error(e.message ?: "Error desconocido") }
        }
    }
}
