package com.example.api.ui.theme.telas.orcamento

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.api.R
import com.example.api.ui.theme.APITheme

@Composable
fun Orcamento(modifier: Modifier = Modifier) {
    Column {
        Column(
            modifier = Modifier
                .width(412.dp)
                .height(138.dp)
                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(0.dp))
                .padding(start = 80.dp, top = 32.dp, end = 80.dp, bottom = 32.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .width(92.dp)
                    .height(36.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Descrição da Imagem",
                    modifier = Modifier
                        .fillMaxSize(),
                    contentScale = ContentScale.Fit
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Título
            Text(
                text = "Orçamento",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC54477)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth()
            )

            Text(
                text = "Selecione o horário e data da sua reserva e a quantidade de pessoas.",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(vertical = 8.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            var data by remember { mutableStateOf("01 de Maio de 2025") }
            var horario by remember { mutableStateOf("16:30") }
            var quantidade by remember { mutableStateOf("140") }
            var evento by remember { mutableStateOf("Infantil com Balões") }

            val eventos = listOf("Infantil com Balões", "Casamento", "Corporativo")

            OutlinedTextField(
                value = data,
                onValueChange = { data = it },
                label = { Text("Data") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color(0xFFD9D9D9),
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    disabledBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = horario,
                onValueChange = { horario = it },
                label = { Text("Horário") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color(0xFFD9D9D9),
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    disabledBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(16.dp))

            Box {
                var expanded by remember { mutableStateOf(false) }
                OutlinedTextField(
                    value = evento,
                    onValueChange = { },
                    label = { Text("Evento") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable { expanded = !expanded },
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = Color.Black,
                        unfocusedTextColor = Color.Gray,
                        disabledTextColor = Color.Black,
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        disabledBorderColor = Color.Gray,
                        errorBorderColor = Color.Red,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent
                    )
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    eventos.forEach { item ->
                        DropdownMenuItem(
                            onClick = {
                                evento = item
                                expanded = false
                            },
                            text = { Text(text = item) }
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = quantidade,
                onValueChange = { quantidade = it },
                label = { Text("Quantidade de Convidados") },
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Gray,
                    focusedBorderColor = Color(0xFFD9D9D9),
                    unfocusedBorderColor = Color(0xFFD9D9D9),
                    disabledBorderColor = Color.Gray,
                    errorBorderColor = Color.Red
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = {},
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC54477),
                    contentColor = Color.White
                )
            ) {
                Text(text = "Avançar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Cancelar",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.clickable { /* Implementar ação de cancelar */ }
            )
        }
    }
}




@Preview(showBackground = true)
@Composable
fun OrcamentoPreview() {
    APITheme {
        Orcamento()
    }
}