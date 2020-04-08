package com.uesleiramos.e_commerce.infrastructure.event.response

data class Marketplace(
    val maiorPreco: Int,
    val menorPreco: Int,
    val lojistaPadrao: LojistaPadrao,
    val lojistasEmDestaque: List<LojistasEmDestaque>
)