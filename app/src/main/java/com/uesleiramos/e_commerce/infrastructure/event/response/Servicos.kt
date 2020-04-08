package com.uesleiramos.e_commerce.infrastructure.event.response

data class Servicos(
    val nome: String,
    val sku: Int,
    val idLojista: Int,
    val preco: Int,
    val parcelamento: String,
    val tipo: String
)