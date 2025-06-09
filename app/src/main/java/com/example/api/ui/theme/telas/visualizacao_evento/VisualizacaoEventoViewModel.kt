package com.example.api.ui.theme.telas.visualizacao_evento

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.request.orcamento.OrcamentoRequest
import com.example.api.data.model.response.decoracao.DecoracaoResponse
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.ApiClient
import com.example.api.data.repository.decoracao.DecoracaoRepository
import com.example.api.data.repository.orcamento.OrcamentoRepository
import com.example.api.data.session.SessaoUsuario
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class VisualizacaoEventoViewModel : ViewModel() {

    private val repository = OrcamentoRepository()
    private val decoracaoRepository = DecoracaoRepository()

    private val _orcamento = MutableStateFlow<OrcamentoResponse?>(null)
    val orcamento: StateFlow<OrcamentoResponse?> = _orcamento

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _erroMsg = MutableStateFlow<String?>(null)
    val erroMsg: StateFlow<String?> = _erroMsg

    private val _orcamentoSalvo = MutableStateFlow(false)
    val orcamentoSalvo: StateFlow<Boolean> = _orcamentoSalvo

    private val _decoracoes = MutableStateFlow<List<DecoracaoResponse>>(emptyList())
    val decoracoes: StateFlow<List<DecoracaoResponse>> = _decoracoes

    private val _decoracaoSelecionadaId = MutableStateFlow<Int?>(null)
    val decoracaoSelecionadaId: StateFlow<Int?> = _decoracaoSelecionadaId

    fun carregarOrcamento(id: Int, token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.getOrcamento(id, token)
            _isLoading.value = false

            result.onSuccess {
                _orcamento.value = it
            }.onFailure {
                _erroMsg.value = "Erro ao carregar orçamento"
            }
        }
    }

    fun buscarDecoracoesPorTipoEvento(tipoEventoId: Int) {
        viewModelScope.launch {
            try {
                val response = decoracaoRepository.listarPorTipo(tipoEventoId)
                _decoracoes.value = response
            } catch (e: Exception) {
                _decoracoes.value = emptyList()
            }
        }
    }

    fun selecionarDecoracao(id: Int?) {
        _decoracaoSelecionadaId.value = id
    }

    fun atualizarOrcamento(id: Int, request: OrcamentoRequest, token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = repository.atualizarOrcamento(id, request, token)
            _isLoading.value = false

            result.onSuccess {
                _orcamento.value = it
            }.onFailure {
                _erroMsg.value = it.message ?: "Erro ao atualizar orçamento"
            }
        }
    }

    fun confirmarAtualizacao(
        decoracaoId: Int?,
        novaData: String,
        novaQtdConvidados: Int,
        token: String
    ) {
        val orcamentoAtual = _orcamento.value ?: return
        val decoracaoIdSelecionada = _decoracaoSelecionadaId.value

        val request = OrcamentoRequest(
            dataEvento = novaData,
            qtdConvidados = novaQtdConvidados,
            inicio = orcamentoAtual.inicio,
            sugestao = orcamentoAtual.sugestao,
            tipoEventoId = orcamentoAtual.tipoEvento.id,
            usuarioId = orcamentoAtual.usuario.id,
            decoracaoId = decoracaoIdSelecionada,
            buffetId = orcamentoAtual.buffet.id,
            enderecoId = orcamentoAtual.endereco.id
        )

        println("Data: $novaData")
        println("Token:" + SessaoUsuario.token)
        println("Quantidade: ${novaQtdConvidados}")
        println("Decoração ID: $decoracaoId")

        atualizarOrcamento(orcamentoAtual.id, request, token)
    }
}
