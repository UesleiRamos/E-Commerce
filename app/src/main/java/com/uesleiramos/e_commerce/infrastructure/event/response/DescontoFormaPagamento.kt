package com.uesleiramos.e_commerce.infrastructure.event.response

data class DescontoFormaPagamento(
    val preco: Int,
    val possuiDesconto: Boolean,
    val descricao: String,
    val porcentagemDesconto: Int
)