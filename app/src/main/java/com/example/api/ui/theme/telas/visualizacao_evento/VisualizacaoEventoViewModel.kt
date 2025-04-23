package com.example.api.ui.theme.telas.visualizacao_evento

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.ApiClient
import com.example.api.data.repository.orcamento.OrcamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class VisualizacaoEventoViewModel : ViewModel() {

    private val repository = OrcamentoRepository()

    private val _orcamento = MutableStateFlow<OrcamentoResponse?>(null)
    val orcamento: StateFlow<OrcamentoResponse?> = _orcamento

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _erroMsg = MutableStateFlow<String?>(null)
    val erroMsg: StateFlow<String?> = _erroMsg

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
}