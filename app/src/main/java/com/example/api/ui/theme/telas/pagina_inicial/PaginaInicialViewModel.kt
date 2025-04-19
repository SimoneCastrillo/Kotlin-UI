package com.example.api.ui.theme.telas.pagina_inicial

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PaginaInicialViewModel : ViewModel() {
    private val _id = mutableStateOf<Int?>(null)
    val id: State<Int?> get() = _id

    private val _token = mutableStateOf<String?>(null)
    val token: State<String?> get() = _token

    fun setUsuarioLogado(id: Int, token: String) {
        _id.value = id
        _token.value = token
    }
}