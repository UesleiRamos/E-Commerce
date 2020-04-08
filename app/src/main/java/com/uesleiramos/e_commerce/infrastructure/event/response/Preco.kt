package com.uesleiramos.e_commerce.infrastructure.event.response

data class Preco(
    val planoPagamento: String,
    val valorParcela: Float,
    val quantidadeMaximaParcelas: Int,
    val precoAtual: Float,
    val precoAnterior: Float,
    val porcentagemDesconto: Int,
    val descontoFormaPagamento: DescontoFormaPagamento?
)