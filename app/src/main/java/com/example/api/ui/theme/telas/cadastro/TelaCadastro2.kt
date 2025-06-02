package com.example.api.ui.theme.telas.cadastro

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.data.model.request.usuario.UsuarioCadastroRequest
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.TopoLogo

@Composable
fun Cadastro2(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavController,
    backStackEntry: NavBackStackEntry
) {
    val viewModel: CadastroViewModel = viewModel()
    val nome = backStackEntry.arguments?.getString("nome") ?: ""
    val telefone = backStackEntry.arguments?.getString("telefone") ?: ""
    var passwordVisible by remember { mutableStateOf(false) }
    var passwordConfirmationVisible by remember { mutableStateOf(false) }

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var emailConfirmacao by remember { mutableStateOf("") }
    var senhaConfirmacao by remember { mutableStateOf("") }

    var erro by remember { mutableStateOf<String?>(null) }

    var erroEmail by remember { mutableStateOf<String?>(null) }
    var erroSenha by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = modifier
            .fillMaxHeight()
            .verticalScroll(rememberScrollState())
    ) {
        TopoLogo()

        Box(
            modifier = Modifier
                .padding(horizontal = 64.dp, vertical = 32.dp)
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {

                erro?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                // Campo Email
                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    label = { Text("Insira seu e-mail") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        unfocusedContainerColor = Color(0xFFF6F6F6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Campo Confirmação Email
                OutlinedTextField(
                    value = emailConfirmacao,
                    onValueChange = { emailConfirmacao = it },
                    label = { Text("Confirme seu e-mail") },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        unfocusedContainerColor = Color(0xFFF6F6F6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                if (erroEmail != null) {
                    Text(
                        text = erroEmail!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                // Campo Senha
                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
                    label = { Text("Insira sua senha") },
                    visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { passwordVisible = !passwordVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        unfocusedContainerColor = Color(0xFFF6F6F6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )

                // Campo Confirmação Senha
                OutlinedTextField(
                    value = senhaConfirmacao,
                    onValueChange = { senhaConfirmacao = it },
                    label = { Text("Confirme sua senha") },
                    visualTransformation = if (passwordConfirmationVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        val icon = if (passwordConfirmationVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                        IconButton(onClick = { passwordConfirmationVisible = !passwordConfirmationVisible }) {
                            Icon(imageVector = icon, contentDescription = null)
                        }
                    },
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Color(0xFFD9D9D9),
                        unfocusedBorderColor = Color(0xFFD9D9D9),
                        focusedContainerColor = Color(0xFFF6F6F6),
                        unfocusedContainerColor = Color(0xFFF6F6F6)
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
                if (erroSenha != null) {
                    Text(
                        text = erroSenha!!,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(start = 4.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .padding(bottom = 12.dp)
                            .wrapContentWidth(Alignment.CenterHorizontally)
                    )
                }

                Spacer(modifier = Modifier.height(32.dp))

                // Botão Cadastrar
                Button(
                    onClick = {
                        // Limpa erros anteriores
                        erroEmail = null
                        erroSenha = null

                        var valido = true

                        if (email != emailConfirmacao) {
                            erroEmail = "Os e-mails não coincidem"
                            valido = false
                        }

                        if (senha != senhaConfirmacao) {
                            erroSenha = "As senhas não coincidem"
                            valido = false
                        }

                        if (valido) {
                            val usuario = UsuarioCadastroRequest(
                                nome = nome,
                                email = email,
                                senha = senha,
                                telefone = telefone
                            )
                            viewModel.cadastrarUsuario(usuario, navController)
                        }
                    },

                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp)
                        .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 16.dp))
                ) {
                    Text(
                        text = "Cadastrar",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(800),
                            color = Color.White,
                            textAlign = TextAlign.Center
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Texto de rodapé (Login)
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(40.dp)
                ) {
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("login")
                            },
                        text = buildAnnotatedString {
                            append("Já possui uma conta? ")
                            withStyle(style = SpanStyle(color = Color(0xFFC54477))) {
                                append("Login")
                            }
                        },
                        style = TextStyle(
                            fontSize = 11.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFF000000),
                            textAlign = TextAlign.Center
                        )
                    )
                }
            }
        }
    }
}
