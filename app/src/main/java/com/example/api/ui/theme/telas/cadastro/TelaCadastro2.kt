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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import com.example.api.R
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

    Column {
       TopoLogo()

        Box(
            modifier = Modifier
                .width(412.dp)
                .height(543.dp)
                .padding(start = 64.dp, top = 32.dp, end = 64.dp, bottom = 64.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                erro?.let {
                    Text(
                        text = it,
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(top = 8.dp)
                    )
                }

                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        singleLine = true,
                        label = { Text(stringResource(R.string.insira_seu_email)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = emailConfirmacao,
                        onValueChange = { emailConfirmacao = it },
                        singleLine = true,
                        label = { Text(stringResource(R.string.confirme_seu_email)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text(stringResource(R.string.insira_sua_senha)) },
                        visualTransformation = if (passwordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { passwordVisible = !passwordVisible }) {
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp)
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = senhaConfirmacao,
                        onValueChange = { senhaConfirmacao = it },
                        label = { Text(stringResource(R.string.confirme_sua_senha)) },
                        visualTransformation = if (passwordConfirmationVisible) VisualTransformation.None else PasswordVisualTransformation(),
                        trailingIcon = {
                            val icon = if (passwordConfirmationVisible) Icons.Default.Visibility else Icons.Default.VisibilityOff
                            IconButton(onClick = { passwordConfirmationVisible = !passwordConfirmationVisible }) {
                                Icon(imageVector = icon, contentDescription = null)
                            }
                        },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(31.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp)
                ){
                    Button(
                        onClick = {
                            erro = null

                            if (email != emailConfirmacao) {
                                erro = "Os e-mails não coincidem"
                            }

                            if (senha != senhaConfirmacao) {
                                erro = "As senhas não coincidem"
                            }

                            val usuario = UsuarioCadastroRequest(
                                nome = nome,
                                email = email,
                                senha = senha,
                                telefone = telefone
                            )

                            viewModel.cadastrarUsuario(usuario, navController)
                        },
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent,
                        ),
                        modifier = Modifier
                            .width(412.dp)
                            .height(40.dp)
                            .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 16.dp))
                    ){
                        Text(
                            text = "Cadastrar",
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(800),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

//                Spacer(modifier = Modifier.weight(1f))
                  Spacer(modifier = Modifier.height(24.dp))


                Box(
                    modifier = Modifier
                        .width(284.dp)
                        .height(13.dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable{
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
                            textAlign = TextAlign.Center,
                        )
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreviewCadastro2() {
//    val navController = rememberNavController()
//
//    APITheme {
//        val backStackEntry = navController.getBackStackEntry("cadastro-2")
//        Cadastro2("Android", navController = navController, backStackEntry =  NavBackStackEntry)
//    }
}