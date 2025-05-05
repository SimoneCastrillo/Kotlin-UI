package com.example.api.ui.theme.telas.orcamento

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme
import androidx.compose.runtime.collectAsState

import com.example.api.ui.theme.telas.login.Login
import kotlinx.coroutines.flow.forEach

@Composable
fun Orcamento(modifier: Modifier = Modifier, navController: NavController) {
    val viewModel = OrcamentoViewModel()
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val tiposEvento by viewModel.tiposEvento.collectAsState(emptyList()) // Coleta os tipos de evento da ViewModel

    var evento by remember { mutableStateOf("") } // Estado para o evento selecionado
    var expanded by remember { mutableStateOf(false) } // E
    val data = remember { mutableStateOf("01 de Maio de 2025") }
    val horario = remember { mutableStateOf("16:30") }
    val quantidade = remember { mutableStateOf("140") }

    LaunchedEffect(Unit) {
        viewModel.carregarTiposEvento()
    }

    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth() // Ajusta a largura para o máximo na tela
                .height(138.dp)
                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(0.dp))
                .padding(start = 16.dp, top = 32.dp, end = 16.dp, bottom = 32.dp), // Use padding ao invés de alinhamento fixo
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            Box(
                modifier = Modifier
                    .width((screenWidth * 0.25f)) // Ajuste baseado em porcentagem da largura da tela
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

            var data by remember { mutableStateOf("2025-04-27") }
            var horario by remember { mutableStateOf("16:30:00") }
            var quantidade by remember { mutableStateOf("140") }
            var evento by remember { mutableStateOf("Selecione o tipo de evento") }
            var eventoId by remember { mutableStateOf<Int?>(null) }

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
                OutlinedTextField(
                    value = evento,
                    onValueChange = { /* Não deve ser editável diretamente */ },
                    label = { Text("Tipo de Evento") },
                    readOnly = true,
                    modifier = Modifier.fillMaxWidth(),
                    trailingIcon = {
                        Icon(
                            Icons.Filled.ArrowDropDown, contentDescription = null,
                            modifier = Modifier.clickable { expanded = !expanded })
                    }
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (tiposEvento.isNotEmpty()) {
                        tiposEvento.forEach { tipo ->
                            DropdownMenuItem(
                                onClick = {
                                    evento = tipo.nome
                                    eventoId = tipo.id// Atualiza o nome do evento selecionado
                                    expanded = false // Fecha o menu
                                    // Aqui você pode fazer algo com o 'tipo' selecionado,
                                    // como atualizar um estado que guarda o objeto TipoEvento completo.
                                },
                                text = { Text(text = tipo.nome) }
                            )
                        }
                    } else {
                        DropdownMenuItem(
                            onClick = {},
                            enabled = false,
                            text = { Text(text = "Nenhum evento disponível") }
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
                onClick = {
                    val url = "tela-orcamento2/$eventoId/$data/$horario/$quantidade"

                    navController.navigate(url)
                },
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

//@Composable
//fun TipoEventoDropdown(viewModel: OrcamentoViewModel) {
//    val expanded = remember { mutableStateOf(false) }
//
//    LaunchedEffect(Unit) {
//        viewModel.carregarTiposEvento()
//    }
//
//    Box {
//        OutlinedTextField(
//            value = viewModel.eventoSelecionadoNome,
//            onValueChange = {},
//            label = { Text("Evento") },
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable { expanded.value = true },
//            readOnly = true
//        )
//
//        DropdownMenu(
//            expanded = expanded.value,
//            onDismissRequest = { expanded.value = false }
//        ) {
//            if (tiposEvento.isNotEmpty()) {
//                tiposEvento.forEach { tipo ->
//                    // Para cada tipo de evento, cria-se um item no menu
//                    DropdownMenuItem(
//                        onClick = {
//                            evento = tipo.nome // Atualiza o evento selecionado
//                            expanded = false // Fecha o menu
//                        },
//                        text = { Text(text = tipo.nome) }
//                    )
//                }
//            } else {
//                // Caso a lista esteja vazia, exibe uma mensagem
//                DropdownMenuItem(
//                    onClick = {},
//                    text = { Text(text = "Nenhum evento disponível") }
//                )
//            }
//        }
//    }
//}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrcamentoPreview() {
    val navController = rememberNavController();
    APITheme {
        Orcamento(navController = navController)
    }
}