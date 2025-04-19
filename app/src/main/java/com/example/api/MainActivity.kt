package com.example.api

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.api.ui.theme.APITheme
import com.example.api.ui.theme.telas.cadastro.Cadastro
import com.example.api.ui.theme.telas.cadastro.Cadastro2
import com.example.api.ui.theme.telas.login.Login
import com.example.api.ui.theme.telas.redefinicao_senha.RedefinirSenha1
import com.example.api.ui.theme.telas.redefinicao_senha.RedefinirSenha2
import com.example.api.ui.theme.telas.redefinicao_senha.RedefinirSenha3
import com.example.api.ui.theme.telas.visualizacao_evento.TelaVisualizacaoEvento
import com.example.api.ui.theme.telas.orcamento.Orcamento
import com.example.api.ui.theme.telas.orcamento.Orcamento2Screen
import com.example.api.ui.theme.telas.orcamento.OrcamentoPreview2
import com.example.api.ui.theme.telas.pagina_inicial.TelaPaginaInicial
import com.example.api.ui.theme.telas.perfil.PerfilScreen
import com.example.api.ui.theme.telas.perfil.TelaPerfil


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            NavHost(navController = navController, startDestination = "cadastro-1") {
                composable("login") {
                    Login("Android", navController = navController)
                }

                composable("cadastro-1") {
                    Cadastro("Android", navController = navController)
                }

                composable("cadastro-2") {
                    Cadastro2("Android", navController = navController)
                }

                composable("redefinir-senha-1") {
                    RedefinirSenha1("Android", navController = navController)
                }

                composable("redefinir-senha-2") {
                    RedefinirSenha2("Android", navController = navController)
                }

                composable("redefinir-senha-3") {
                    RedefinirSenha3("Android", navController = navController)
                }

                composable("visualizacao-evento") {
                    TelaVisualizacaoEvento("Android", navController = navController)
                }

                composable("tela-orcamento"){
                    Orcamento("Android", navController = navController)
                }

                composable("tela-orcamento2"){
                    Orcamento2Screen("Android", navController = navController)
                }

                composable("tela-perfil/{id}/{token}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    val token = backStackEntry.arguments?.getString("token")

                    if (id != null && token != null) {
                        PerfilScreen(id = id, token = token, navController = navController)
                    }
                }

                composable("pagina-inicial/{id}/{token}") { backStackEntry ->
                    val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
                    val token = backStackEntry.arguments?.getString("token")

                    if (id != null && token != null) {
                        TelaPaginaInicial(id = id, token = token, navController = navController)
                    }
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
        Cadastro("Android", navController = navController)
    }
}