package com.example.api.ui.theme.telas.orcamento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.api.R

@Composable
fun Orcamento2Screen() {
    var showDialog by remember { mutableStateOf(false) }
    var decoracao by remember { mutableStateOf("") }
    var observacao by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho
        Header()

        // Título
        Text(
            text = "Orçamento",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFFC54477),
                textAlign = TextAlign.Center
            ),
            modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
        )

        Text(
            text = "Observações (Opcional)",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Campo Decoração
        OutlinedTextField(
            value = decoracao,
            onValueChange = { decoracao = it },
            label = { Text("Decoração") },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showDialog = true },
            readOnly = true,
            colors = OutlinedTextFieldDefaults.colors()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo Observação
        OutlinedTextField(
            value = observacao,
            onValueChange = { observacao = it },
            label = { Text("Observação") },
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp),
            colors = OutlinedTextFieldDefaults.colors()
        )

        Spacer(modifier = Modifier.height(24.dp))

        // Botão Finalizar
        Button(
            onClick = { /* Lógica para finalizar */ },
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFFC54477),
                contentColor = Color.White
            )
        ) {
            Text(text = "Finalizar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Voltar",
            style = TextStyle(
                fontSize = 14.sp,
                color = Color.Gray
            ),
            modifier = Modifier.clickable { /* Lógica para voltar */ }
        )
    }

    // Modal de Decoração
    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(250.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Decorações",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC54477),
                            textAlign = TextAlign.Center
                        )
                    )

                    Column(
                        modifier = Modifier.padding(vertical = 8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Gray)
                        )

                        Box(
                            modifier = Modifier
                                .size(100.dp)
                                .background(Color.Gray)
                        )
                    }

                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC54477),
                            contentColor = Color.White
                        )
                    ) {
                        Text(text = "Selecionar")
                    }
                }
            }
        }
    }
}

