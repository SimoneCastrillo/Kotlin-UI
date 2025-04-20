package com.example.api.ui.theme.telas.perfil

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.api.data.model.response.login.LoginResponse
import com.example.api.data.repository.usuario.UsuarioRepository
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.request.usuario.UsuarioUpdateRequest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody


class PerfilViewModel : ViewModel() {
    private val _usuario = MutableStateFlow<LoginResponse?>(null)
    val usuario: StateFlow<LoginResponse?> = _usuario


    var isLoading by mutableStateOf(false)
        private set

    var erroMsg by mutableStateOf<String?>(null)
        private set

    var sucessoMsg by mutableStateOf<String?>(null)
        private set

    private val repository = UsuarioRepository()

    fun carregarPerfil(id: Int, token: String) {
        viewModelScope.launch {
            isLoading = true
            erroMsg = null

            val result = repository.buscarPerfil(id, token)

            result.onSuccess {
                _usuario.value = it
            }.onFailure {
                erroMsg = it.message
            }

            isLoading = false
        }
    }

    fun salvarPerfil(id: Int, request: UsuarioUpdateRequest, token: String, fotoPart: MultipartBody.Part?) {
        viewModelScope.launch {
            try {
                repository.atualizarPerfil(id, request, token, fotoPart)
                carregarPerfil(id, token)

                sucessoMsg = "Dados atualizados com sucesso"
                erroMsg = null
            } catch (e: Exception) {
                erroMsg = "Erro ao salvar perfil: ${e.message}"
                sucessoMsg = null
            }
        }
    }
}