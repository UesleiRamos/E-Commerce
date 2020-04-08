package com.uesleiramos.e_commerce.infrastructure.event.response

import java.io.Serializable

data class ProductDetailResponse(
    val id: Int,
    val nome: String,
    val descricao: String,
    val retiraEmLoja: Boolean,
    val categorias: List<Categorias>,
    val maisInformacoes: List<MaisInformacoes>,
    val marca: Marca,
    val modelo: Modelo,
    val urlVideo: String?
): Serializable {}