package com.example.api.ui.theme.telas.orcamento

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavController
import com.example.api.R
import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.session.SessaoUsuario
import com.example.api.ui.theme.components.util.LoadingDialog

@Composable
fun Orcamento2Screen(
    modifier: Modifier = Modifier,
    navController: NavController,
    backStackEntry: NavBackStackEntry,
) {
    val viewModelTela2: Orcamento2ViewModel = viewModel()

    val context = LocalContext.current

    LaunchedEffect(viewModelTela2.sucessoMsg, viewModelTela2.erroMsg) {
        viewModelTela2.sucessoMsg?.let {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()
        }
    }

    var showDialog by remember { mutableStateOf(false) }
    var observacao by remember { mutableStateOf("") }

    val tipoEventoId = backStackEntry.arguments?.getInt("tipoEventoId")
    val dataRecebida = backStackEntry.arguments?.getString("data")
    val horarioRecebido = backStackEntry.arguments?.getString("horario")
    val quantidadeRecebida = backStackEntry.arguments?.getString("quantidade")?.toIntOrNull() ?: 0

    LaunchedEffect(tipoEventoId) {
        tipoEventoId?.let {
            viewModelTela2.buscarDecoracoesPorTipoEvento(it)
        }
    }

    val decoracoes by viewModelTela2.decoracoes.collectAsState()
    val decoracaoSelecionadaId by viewModelTela2.decoracaoSelecionadaId.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Cabeçalho (mesmo da sua tela)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(138.dp)
                .background(color = Color(0xFFC54477), shape = RoundedCornerShape(0.dp)),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_branco),
                contentDescription = "Logo",
                modifier = Modifier.size(92.dp),
                contentScale = ContentScale.Fit
            )
        }

        // Conteúdo Principal
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Orçamento",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFFC54477),
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
            )

            Text(
                text = "Selecione a decoração desejada (opcional).",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.padding(bottom = 16.dp)
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(Color(0xFFFFFFFF), shape = RoundedCornerShape(8.dp))
                    .border(1.dp, Color.Gray, shape = RoundedCornerShape(8.dp))
                    .clickable { showDialog = true }
                    .padding(horizontal = 16.dp, vertical = 16.dp),
                contentAlignment = Alignment.CenterStart
            ) {
                val textoDecoracao = decoracaoSelecionadaId?.let { id ->
                    decoracoes.find { it.id == id }?.let { "Decoração: ${it.nome}" } ?: "Decoração (Opcional)"
                } ?: "Decoração (Opcional)"
                Text(textoDecoracao, style = TextStyle(color = Color.DarkGray, fontSize = 16.sp))
            }

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = observacao,
                onValueChange = { observacao = it },
                label = { Text("Observação") },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFC54477),
                    unfocusedBorderColor = Color.Gray
                )
            )

            Spacer(modifier = Modifier.height(24.dp))

            val dataEvento = dataRecebida ?: ""
            val inicio = horarioRecebido ?: ""
            val qtdConvidados = quantidadeRecebida
            val tipoEventoIdFinal = tipoEventoId ?: 0 // Tratar caso seja nulo
            val decoracaoIdFinal = decoracaoSelecionadaId
            val observacaoFinal = observacao.takeIf { it.isNotBlank() }

            if (viewModelTela2.isLoading) {
                LoadingDialog("Finalizando...")
            }

            Button(
                onClick = {
                    viewModelTela2.setDadosOrcamento(
                        data = dataRecebida ?: "",
                        horario = horarioRecebido ?: "",
                        quantidade = quantidadeRecebida,
                        tipoEventoId = tipoEventoId ?: 0,
                        sugestao = observacao.takeIf { it.isNotBlank() },
                        decoracaoId = decoracaoSelecionadaId
                    )

                    val orcamentoRequest = OrcamentoRequest(
                        dataEvento = viewModelTela2.dataEvento,
                        inicio = viewModelTela2.horario,
                        qtdConvidados = viewModelTela2.qtdConvidados,
                        tipoEventoId = viewModelTela2.tipoEventoId,
                        usuarioId = viewModelTela2.usuarioId,
                        sugestao = viewModelTela2.sugestao,
                        decoracaoId = viewModelTela2.decoracaoId
                    )

                    viewModelTela2.cadastrarOrcamento(orcamentoRequest, navController)
                    println("Finalizar Orçamento:")
                    println("Tipo Evento ID: $tipoEventoIdFinal")
                    println("Data: $dataEvento")
                    println("User:" + viewModelTela2.usuarioId)
                    println("Token:" + SessaoUsuario.token)
                    println("Horário: $inicio")
                    println("Quantidade: $qtdConvidados")
                    println("Decoração ID: $decoracaoIdFinal")
                    println("Observação: $observacaoFinal")
                },
                enabled = !viewModelTela2.isLoading,
                modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFC54477),
                    contentColor = Color.White
                )
            ) {
                var textoBotao = "Finalizar"; if (decoracaoSelecionadaId == null) {
                    textoBotao = "Finalizar sem decoração"
                }
                Text(
                    text = textoBotao,
                    style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = "Voltar",
                style = TextStyle(
                    fontSize = 14.sp,
                    color = Color.Gray
                ),
                modifier = Modifier.clickable { navController.popBackStack() }
            )
        }
    }

    if (showDialog) {
        Dialog(onDismissRequest = { showDialog = false }) {
            Box(
                modifier = Modifier
                    .size(300.dp)
                    .background(Color.White, shape = MaterialTheme.shapes.medium)
                    .padding(16.dp)
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.SpaceBetween,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Decorações",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFFC54477),
                            textAlign = TextAlign.Center
                        )
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(2),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.weight(1f)

                    ) {

                        if (decoracoes.isEmpty()) {
                            item(span = { GridItemSpan(maxLineSpan) }) {
                                Text(
                                    text = "Nenhuma decoração disponível para este tipo de evento.",
                                    color = Color.Gray,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp),
                                    textAlign = TextAlign.Center,
                                    style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }

                        items(decoracoes) { decoracao ->
                            val base64Image = decoracao.foto
                            val imageBytes = android.util.Base64.decode(base64Image, android.util.Base64.DEFAULT)

                            val imageBitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)

                            Column {
                                Text(
                                    text = decoracao.nome,
                                    style = TextStyle(
                                        fontSize = 16.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color(0xFFC54477),
                                        textAlign = TextAlign.Center
                                    ),
                                    modifier = Modifier
                                        .padding(bottom = 8.dp, top = 8.dp)
                                )

                                Box(
                                    modifier = Modifier
                                        .aspectRatio(1f)
                                        .background(Color.LightGray)
                                        .clickable {
                                            if (decoracaoSelecionadaId == decoracao.id) {
                                                viewModelTela2.selecionarDecoracao(null) // ← desmarca se já estiver selecionada
                                            } else {
                                                viewModelTela2.selecionarDecoracao(decoracao.id)
                                            }
                                        }
                                ) {
                                    Image(
                                        bitmap = imageBitmap.asImageBitmap(),
                                        contentDescription = decoracao.nome,
                                        modifier = Modifier.fillMaxSize(),
                                        contentScale = ContentScale.Crop
                                    )

                                    if (decoracaoSelecionadaId == decoracao.id) {
                                        Box(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .background(Color.Black.copy(alpha = 0.3f)),
                                            contentAlignment = Alignment.Center
                                        ) {
                                            Icon(Icons.Default.CheckCircle, contentDescription = "Selecionado", tint = Color.White, modifier = Modifier.size(36.dp))
                                        }
                                    }
                                }
                            }
//
                        }
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    Button(
                        onClick = { showDialog = false },
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFC54477),
                            contentColor = Color.White
                        )
                    ) {
                        var textoBotao = "Selecionar"; if (decoracaoSelecionadaId == null) {
                        textoBotao = "Fechar"
                    }
                        Text(text = textoBotao)
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun OrcamentoPreview2() {
//    val navController = rememberNavController();
//    APITheme {
//        val navController = rememberNavController()
//        val backStackEntry = NavBackStackEntry(
//            destination = null,
//            context = null,
//            destinationId = 0,
//        )
//        Orcamento2Screen(navController = navController)
//    }
}