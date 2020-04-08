package com.uesleiramos.e_commerce.infrastructure.event.response

data class LojistaPadrao(
    val id: Int,
    val nome: String,
    val preco: Int,
    val retiraRapido: Boolean,
    val compraOnline: Boolean,
    val eleito: Boolean
)