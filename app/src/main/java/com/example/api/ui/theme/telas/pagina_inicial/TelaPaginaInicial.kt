package com.example.api.ui.theme.telas.pagina_inicial

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
import com.example.api.ui.theme.components.MenuIcones

@Composable
fun TelaPaginaInicial(name: String, modifier: Modifier = Modifier, navController: NavController) {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    Box(modifier = Modifier.fillMaxSize()) {
        // Cabeçalho
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0xFFC54477))
                .padding(vertical = 32.dp, horizontal = 16.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = "Descrição da Imagem",
                modifier = Modifier
                    .height(36.dp)
                    .fillMaxWidth(),
                contentScale = ContentScale.Fit
            )
        }

        // Conteúdo
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 100.dp, bottom = screenHeight * 0.1f, start = 16.dp, end = 16.dp)
                .align(Alignment.BottomCenter),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Text(
                text = "Reservas",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC54477)
                ),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Lista de eventos
            val events = listOf(
                "Aniversário" to "22/03/2025",
                "Coffee Break" to "28/05/2025",
                "Coffee Break" to "22/03/2025"
            )

            events.forEach { (label, date) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(
                            1.dp,
                            color = Color(0xFFD9D9D9),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .background(
                            color = Color(0x33D9D9D9),
                            shape = RoundedCornerShape(size = 8.dp)
                        )
                        .padding(16.dp)
                        .clickable { navController.navigate("visualizacao-evento") },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        val imageRes =
                            if (label == "Coffee Break" && date == "22/03/2025") R.drawable.cancelado else R.drawable.pendente
                        Image(
                            painter = painterResource(id = imageRes),
                            contentDescription = "Descrição da Imagem",
                            modifier = Modifier.size(17.dp),
                            contentScale = ContentScale.Fit
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text(
                            text = label,
                            style = TextStyle(
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }
                    Text(
                        text = "Data: $date",
                        style = TextStyle(
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Light,
                            color = Color(0xFF000000)
                        )
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
            }

            Spacer(modifier = Modifier.height(screenHeight * 0.1f))

            MenuIcones(navController)

        }
    }
    }

@Preview(showBackground = true)
@Composable
fun PreviewTelaPaginaInicial() {
    val navController = rememberNavController()

    APITheme {
        TelaPaginaInicial("Android", navController = navController)
    }
}