package com.uesleiramos.e_commerce.infrastructure.event.response

import java.io.Serializable

data class Product(
    val id: Int,
    val sku: Int,
    val nome: String,
    val disponivel:Boolean,
    val descricao:String,
    val imagemUrl: String,
    val classificacao:Int,
    val preco: Preco
): Serializable {}