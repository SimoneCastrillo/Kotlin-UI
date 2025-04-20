package com.example.api.ui.theme.telas.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.TopoLogo
import com.example.api.ui.theme.telas.pagina_inicial.PaginaInicialViewModel

@Composable
fun Login(name: String, modifier: Modifier = Modifier, navController: NavController) {
    val viewModelTelaInicial: PaginaInicialViewModel = viewModel()
    val viewModel: LoginViewModel = viewModel()
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var isChecked by remember { mutableStateOf(false) }

    val erro = viewModel.erroMsg
    val isLoading = viewModel.isLoading
    val usuario = viewModel.usuarioLogado

    LaunchedEffect(usuario) {
        if (usuario != null) {
            val id = usuario.id
            val token = usuario.token

            navController.navigate("pagina-inicial/$id/$token") {
                popUpTo("login") { inclusive = true }
            }
        }
    }

    Column {
        TopoLogo()
//        BoxWithConstraints(
//            modifier = Modifier
//                .width(412.dp)
//                .height(307.dp)
//                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, bottomStart = 0.dp, bottomEnd = 0.dp))
//                .padding(start = 80.dp, top = 63.dp, end = 80.dp, bottom = 63.dp)
//        ) {
//            val boxWidth = maxWidth
//            val boxHeight = maxHeight
//
//            Image(
//                painter = painterResource(id = R.drawable.logo_branco),
//                contentDescription = "Descrição da Imagem",
//                modifier = Modifier
//                    .width(boxWidth)
//                    .height(boxHeight),
//                contentScale = ContentScale.Fit
//            )
//        }

        Box(
            modifier = Modifier
                .width(412.dp)
                .height(543.dp)
                .padding(start = 64.dp, top = 64.dp, end = 64.dp, bottom = 64.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()

            ) {
                Box {

                    Box(
                        modifier = Modifier
                            .matchParentSize()
                            .padding(top = 8.dp) // adding some space to the label
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(id = R.string.email)) },
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
                            .padding(top = 8.dp) // adding some space to the label
                            .background(Color(0x33D9D9D9))
                    )

                    OutlinedTextField(
                        value = senha,
                        onValueChange = { senha = it },
                        label = { Text(stringResource(id = R.string.senha)) },
                        colors = OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Color(0xFFD9D9D9),
                            unfocusedBorderColor = Color(0xFFD9D9D9)
                        )
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                erro?.let {
                    val mensagem = when (it) {
                        "invalid_login" -> stringResource(id = R.string.erro_login_invalido)
                        "network_error" -> stringResource(id = R.string.erro_rede)
                        else -> it
                    }

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 32.dp)
                    ) {
                        Text(
                            text = mensagem,
                            color = Color.Red,
                            fontSize = 14.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }

                Box(modifier = Modifier
                    .width(305.dp)
                    .height(13.dp)
                    .padding(start = 5.dp, end = 5.dp)
                ){
                    Row(
                        modifier = Modifier.
                        fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(
                            modifier = Modifier.
                            fillMaxHeight()
                        ){
                            Checkbox(
                                modifier = Modifier
                                    .height(10.dp)
                                    .width(10.dp)
                                    .scale(0.5f),
                                checked = isChecked,
                                onCheckedChange = { checked ->
                                    isChecked = checked
                                }
                            )

                            Spacer(modifier = Modifier.width(4.dp))

                            Text(
                                text = stringResource(id = R.string.salvar_acesso),
                                style = TextStyle(
                                    fontSize = 11.sp,
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFF1E1E1E),
                                    textAlign = TextAlign.Left,
                                    textDecoration = TextDecoration.Underline,
                                )
                            )
                        }

                        Text(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate("redefinir-senha-1")
                                },
                            text = stringResource(id = R.string.esqueci_senha),
                            style = TextStyle(
                                fontSize = 11.sp,
                                fontWeight = FontWeight(400),
                                color = Color(0xFF1E1E1E),

                                textAlign = TextAlign.Right,
                                textDecoration = TextDecoration.Underline,
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.height(46.dp))

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(45.dp)
                ){
                    Button(
                        onClick = {
                            viewModel.login(email, senha)
                        },
                        enabled = !isLoading,
                        colors = ButtonDefaults.buttonColors(
                            Color.Transparent,
                        ),
                        modifier = Modifier
                            .width(412.dp)
                            .height(45.dp)
                            .background(color = Color(0xFFC54477), shape = RoundedCornerShape(size = 16.dp))
                            .padding(start = 16.dp, top = 2.dp, end = 16.dp, bottom = 2.dp)
                    ){
                        Text(
                            text = stringResource(id = R.string.login),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight(800),
                                color = Color.White,
                                textAlign = TextAlign.Center,
                            )
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                Box(
                    modifier = Modifier
                        .width(284.dp)
                        .height(13.dp)
                ){
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate("cadastro")
                            },
                        text = buildAnnotatedString {
                            append(stringResource(id = R.string.ainda_nao_possui_conta))
                            withStyle(style = SpanStyle(color = Color(0xFFC54477))) {
                                append(stringResource(id = R.string.cadastre_se))
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
fun GreetingPreview() {
    val navController = rememberNavController();
    APITheme {
        Login("Android", navController = navController)
    }
}
