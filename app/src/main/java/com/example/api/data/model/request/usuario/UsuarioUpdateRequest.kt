package com.example.api.data.model.request.usuario

data class UsuarioUpdateRequest(
    val nome: String,
    val email: String,
    val telefone: String,
    val foto: String?
)
