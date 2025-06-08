package com.example.api.data.network

import com.example.api.data.network.api_services.decoracao.DecoracaoApiService
import com.example.api.data.network.api_services.orcamento.OrcamentoApiService
import com.example.api.data.network.api_services.tipo_evento.TipoEventoApiService
import com.example.api.data.network.api_services.usuario.UsuarioApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    private const val BASE_URL = "http://3.93.65.234/"

    private val clienteHttp: OkHttpClient by lazy {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        OkHttpClient.Builder().addInterceptor(interceptor).build()
    }

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(clienteHttp)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val usuarioApiService: UsuarioApiService by lazy {
        retrofit.create(UsuarioApiService::class.java)
    }

    val orcamentoApiService: OrcamentoApiService by lazy {
        retrofit.create(OrcamentoApiService::class.java)
    }

    val decoracaoApiService: DecoracaoApiService by lazy {
        retrofit.create(DecoracaoApiService::class.java)
    }

    val tipoEventoApiService: TipoEventoApiService by lazy {
        retrofit.create(TipoEventoApiService::class.java)
    }
}

