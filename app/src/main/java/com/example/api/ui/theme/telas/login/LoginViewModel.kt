package com.example.api.ui.theme.telas.login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.R
import com.example.api.data.model.LoginResponse
import com.example.api.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    var erroMsg by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var token by mutableStateOf<String?>(null)
        private set

    var usuarioLogado by mutableStateOf<LoginResponse?>(null)
        private set


    private val repo = AuthRepository()

    fun login(email: String, senha: String) {
        viewModelScope.launch {
            isLoading = true
            erroMsg = null

            val result = repo.login(email, senha)

            result.onSuccess {
                usuarioLogado = it
            }.onFailure {
                erroMsg = it.message
            }

            isLoading = false
        }
    }
}
