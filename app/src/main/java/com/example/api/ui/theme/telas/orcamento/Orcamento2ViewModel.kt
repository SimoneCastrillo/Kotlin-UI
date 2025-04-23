package com.example.api.ui.theme.telas.orcamento

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.repository.decoracao.DecoracaoRepository
import com.example.api.data.repository.orcamento.OrcamentoRepository
import com.example.api.data.session.SessaoUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class Orcamento2ViewModel() : ViewModel() {
    private val decoracaoRepository = DecoracaoRepository()
    private val orcamentoRepository = OrcamentoRepository()

    var erroMsg by mutableStateOf<String?>(null)
        private set

    var sucessoMsg by mutableStateOf<String?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var dataEvento: String = ""
    var horario: String = ""
    var qtdConvidados: Int = 0
    var tipoEventoId: Int = 0
    var sugestao: String? = null
    var decoracaoId: Int? = null
    val usuarioId = SessaoUsuario.usuarioId ?: 0


    private val _decoracoes = MutableStateFlow<List<DecoracaoResponse>>(emptyList())
    val decoracoes: StateFlow<List<DecoracaoResponse>> = _decoracoes

    private val _decoracaoSelecionadaId = MutableStateFlow<Int?>(null)
    val decoracaoSelecionadaId: StateFlow<Int?> = _decoracaoSelecionadaId

    fun selecionarDecoracao(id: Int?) {
        _decoracaoSelecionadaId.value = id
    }

    fun buscarDecoracoesPorTipoEvento(tipoEventoId: Int) {
        Log.e("Orcamento2ErroEvento", "Tipo de evento recebido na tela 2: $tipoEventoId")
        viewModelScope.launch {

            try {
                val response = decoracaoRepository.listarPorTipo(tipoEventoId)
                _decoracoes.value = response
                Log.e("Lista", "Decorações recebidas: ${_decoracoes.value}")
            } catch (e: Exception) {
                Log.e("Erro API", "Erro ao buscar decorações: ${e.message}")
                _decoracoes.value = emptyList()
            }
        }
    }

    fun cadastrarOrcamento(
        request: OrcamentoRequest,
        navController: NavController
    ) {
        viewModelScope.launch {
            isLoading = true
            erroMsg = null
            sucessoMsg = null

            val result = orcamentoRepository.cadastrarOrcamento(request)

            result.onSuccess { response ->
                sucessoMsg = "Orçamento criado com sucesso!"
                val id = response.id
                val token = SessaoUsuario.token
                navController.navigate("visualizacao-evento/$id/$token")
            }.onFailure {
                erroMsg = it.message
            }

            isLoading = false
        }
    }

    fun setDadosOrcamento(
        data: String,
        horario: String,
        quantidade: Int,
        tipoEventoId: Int,
        sugestao: String?,
        decoracaoId: Int?
    ) {
        this.dataEvento = data
        this.horario = horario
        this.qtdConvidados = quantidade
        this.tipoEventoId = tipoEventoId
        this.sugestao = sugestao
        this.decoracaoId = decoracaoId
    }
}