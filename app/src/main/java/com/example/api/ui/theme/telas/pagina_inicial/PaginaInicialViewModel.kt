package com.example.api.ui.theme.telas.pagina_inicial

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.api.data.model.response.orcamento.OrcamentoResponse
import com.example.api.data.network.ApiClient
import com.example.api.data.repository.orcamento.OrcamentoRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PaginaInicialViewModel(
    private val repository: OrcamentoRepository = OrcamentoRepository()
) : ViewModel() {

    private val _orcamentos = MutableStateFlow<List<OrcamentoResponse>>(emptyList())
    val orcamentos: StateFlow<List<OrcamentoResponse>> = _orcamentos

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading

    private val _erroMsg = MutableStateFlow<String?>(null)
    val erroMsg: StateFlow<String?> = _erroMsg

    fun carregarOrcamentos(idUsuario: Int, token: String) {
        viewModelScope.launch {
            _isLoading.value = true
            _erroMsg.value = null

            val result = repository.buscarOrcamentos(idUsuario, token)
            result.onSuccess {
                _orcamentos.value = it
            }.onFailure {
                _erroMsg.value = it.message
            }

            _isLoading.value = false
        }
    }
}
