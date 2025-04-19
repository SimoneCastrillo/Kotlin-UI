package com.example.api.ui.theme.telas.pagina_inicial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
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
import com.example.api.R
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.components.MenuIcones
import com.example.api.ui.theme.telas.perfil.PerfilViewModel

@Composable
fun TelaPaginaInicial(
    id: Int,
    token: String,
    navController: NavController,
) {
    val viewModel: PaginaInicialViewModel = viewModel()
    val orcamentos by viewModel.orcamentos.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val erroMsg by viewModel.erroMsg.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregarOrcamentos(id, token)
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(Color(0xFFC54477))
                .padding(vertical = 32.dp, horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = null,
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = 80.dp, start = 16.dp, end = 16.dp), // espaço para o menu
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(
                    text = "Reservas",
                    style = TextStyle(fontSize = 32.sp, fontWeight = FontWeight.Bold, color = Color(0xFFC54477)),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 16.dp),
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(16.dp))
            }

            when {
                isLoading -> item {
                    CircularProgressIndicator(modifier = Modifier.padding(16.dp))
                }

                erroMsg != null -> item {
                    Text(
                        text = stringResource(id = R.string.erro_rede),
                        color = Color.Red,
                        modifier = Modifier.padding(16.dp)
                    )
                }

                else -> items(orcamentos) { orcamento ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .border(1.dp, color = Color(0xFFD9D9D9), shape = RoundedCornerShape(8.dp))
                            .background(Color(0x33D9D9D9), shape = RoundedCornerShape(8.dp))
                            .padding(16.dp)
                            .clickable {
                                navController.navigate("visualizacao-evento")
                            },
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            val imageRes = if (orcamento.cancelado == true) R.drawable.cancelado else R.drawable.pendente
                            Image(
                                painter = painterResource(id = imageRes),
                                contentDescription = null,
                                modifier = Modifier.size(17.dp)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(
                                text = orcamento.tipoEvento.nome,
                                fontWeight = FontWeight.Bold,
                                fontSize = 14.sp
                            )
                        }
                        Text(
                            text = "Data: ${orcamento.dataEvento}",
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomCenter)
        ) {
            MenuIcones(navController = navController, id = id, token = token)
        }
    }
}



@Preview(showBackground = true)
@Composable
fun PreviewTelaPaginaInicial() {
    val navController = rememberNavController()

    APITheme {
        TelaPaginaInicial(navController = navController, id = 1, token = "")
    }
}