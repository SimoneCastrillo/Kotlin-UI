package com.example.api.ui.theme.telas.cadastro

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.TelaComTextoLogin

@Composable
fun Cadastro(name: String, modifier: Modifier = Modifier, navController: NavController) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            // Header com imagem
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFC54477))
                    .padding(vertical = 64.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo_branco),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp),
                    contentScale = ContentScale.Fit
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Formulário
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 32.dp)
                    .weight(1f, fill = false), // Garante que a altura ocupe o necessário, mas contribua para o preenchimento
            ) {
                OutlinedTextField(
                    value = nome,
                    onValueChange = { nome = it },
                    label = { Text("Insira seu nome") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9)
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                OutlinedTextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    label = { Text("(00) 91234-5678") },
                    modifier = Modifier.fillMaxWidth(),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9)
                    )
                )

                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(20.dp)
                            .clip(CircleShape)
                            .background(Color(0xFFC54477))
                    ) {
                        Text("1", color = Color.White, fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }

                    Box(
                        modifier = Modifier
                            .width(20.dp)
                            .height(2.dp)
                            .background(Color(0xFFFFB6C1))
                    )

                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .border(width = 2.dp, color = Color(0xFFFFB6C1), shape = CircleShape)
                            .size(20.dp)
                            .clip(CircleShape)
                    ) {
                        Text("2", color = Color(0xFFFFB6C1), fontSize = 12.sp, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(modifier = Modifier.height(32.dp))

                Button(
                    onClick = {
                        if (nome.isBlank() || telefone.isBlank()) {
                            errorMessage = "Por favor, preencha todos os campos."
                        } else {
                            errorMessage = ""
                            val encodedNome = Uri.encode(nome)
                            val encodedTelefone = Uri.encode(telefone)
                            navController.navigate("cadastro-2/$encodedNome/$encodedTelefone")
                        }
                    },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFC54477)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text("Avançar", color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                }

                Spacer(modifier = Modifier.height(64.dp))

                TelaComTextoLogin {
                    navController.navigate("login")
                }

                Spacer(modifier = Modifier.height(32.dp))
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun CadastroPreview() {
    val navController = rememberNavController()
    APITheme {
        Cadastro(name = "Preview", navController = navController)
    }
}
