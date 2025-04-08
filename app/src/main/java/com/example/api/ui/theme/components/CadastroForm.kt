package com.example.api.ui.theme.components

import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun CadastroForm(
    nome: String,
    telefone: String,
    onNomeChange: (String) -> Unit,
    onTelefoneChange: (String) -> Unit
) {
    OutlinedTextField(
        value = nome,
        onValueChange = onNomeChange,
        label = { Text("Insira seu nome") },
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFD9D9D9),
            unfocusedBorderColor = Color(0xFFD9D9D9)
        )
    )

    Spacer(modifier = Modifier.height(16.dp))

    OutlinedTextField(
        value = telefone,
        onValueChange = onTelefoneChange,
        label = { Text("(00) 91234-5678") },
        modifier = Modifier.fillMaxWidth(0.8f),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = Color(0xFFD9D9D9),
            unfocusedBorderColor = Color(0xFFD9D9D9)
        )
    )
}
