package com.example.api.ui.theme.telas.perfil

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberAsyncImagePainter
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.MenuIcones
import com.example.api.ui.theme.telas.orcamento.Orcamento

@Composable
fun PerfilScreen(navController: NavController, id: Int, token: String) {
    val viewModel: PerfilViewModel = viewModel()
    val usuario = viewModel.usuario.collectAsState().value

    LaunchedEffect(key1 = id) {
        viewModel.carregarPerfil(id, token)
    }

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var celular by remember { mutableStateOf("") }

    LaunchedEffect(usuario) {
        usuario?.let {
            nome = it.nome
            email = it.email
            celular = it.telefone
        }
    }

    val qtdOrcamentos = viewModel.usuario.value?.qtdOrcamento?.toString() ?: "0"
    val quantidadeReservas by remember { mutableStateOf(viewModel.usuario.value?.qtdOrcamento?.toString() ?: "") }

    val erroMsg by remember { mutableStateOf(viewModel.erroMsg) }

    BoxWithConstraints {
        val maxWidthSize = maxWidth

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(if (maxWidthSize < 600.dp) 100.dp else 138.dp)
                    .background(color = Color(0xFFC54477)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Logo",
                    modifier = Modifier.size(if (maxWidthSize < 600.dp) 68.dp else 92.dp),
                    contentScale = ContentScale.Fit
                )
            }

            val fotoUrl = usuario?.foto ?: R.drawable.avatar

            Box(
                modifier = Modifier
                    .size(100.dp)
                    .border(2.dp, Color.White, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = rememberAsyncImagePainter(fotoUrl),
                    contentDescription = "Perfil",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Campo Nome
            OutlinedTextField(
                value = nome,
                onValueChange = { nome = it },
                label = { Text(stringResource(R.string.nome)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Campo Email
            OutlinedTextField(
                value = email,
                onValueChange = { email = it }, // Atualiza o email no ViewModel
                label = { Text(stringResource(R.string.email)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = celular,
                onValueChange = { celular = it }, // Atualiza o celular no ViewModel
                label = { Text(stringResource(R.string.celular)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(8.dp))

            OutlinedTextField(
                value = qtdOrcamentos,
                onValueChange = {},
                label = { Text(stringResource(R.string.quantidade_orcamento)) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                ),
                readOnly = true // Torna o campo somente leitura
            )

            erroMsg?.let {
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = it,
                    color = Color.Red,
                    style = TextStyle(fontSize = 14.sp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC54477),
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text("Salvar", style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold))
            }

            Spacer(modifier = Modifier.height(16.dp))

            MenuIcones(navController, id, token)
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun TelaPerfil() {
    val navController = rememberNavController()
    APITheme {
        PerfilScreen(navController = navController, id = 37, token = "")
    }
}