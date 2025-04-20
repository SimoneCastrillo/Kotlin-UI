package com.example.api.ui.theme.telas.cadastro

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.api.data.model.request.usuario.UsuarioCadastroRequest
import com.example.api.data.model.response.usuario.UsuarioCadastroResponse
import com.example.api.data.repository.usuario.UsuarioRepository
import kotlinx.coroutines.launch

class CadastroViewModel : ViewModel() {

    private val repository = UsuarioRepository()

    var erroMsg by mutableStateOf<String?>(null)
        private set

    var sucessoMsg by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var usuarioCadastrado by mutableStateOf<UsuarioCadastroResponse?>(null)
        private set

    fun cadastrarUsuario(
        usuario: UsuarioCadastroRequest,
        navController: NavController
    ) {
        viewModelScope.launch {
            isLoading = true
            erroMsg = null
            sucessoMsg = null

            val result = repository.cadastrarUsuario(usuario)

            result.onSuccess { response ->
                usuarioCadastrado = response
                sucessoMsg = "Usuário cadastrado com sucesso"
                navController.navigate("login") // redireciona após sucesso
            }.onFailure {
                erroMsg = it.message
            }

            isLoading = false
        }
    }
}
