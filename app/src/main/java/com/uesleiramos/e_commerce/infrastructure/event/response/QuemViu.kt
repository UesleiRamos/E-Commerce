package com.uesleiramos.e_commerce.infrastructure.event.response

import java.io.Serializable

data class QuemViu(
    val id : Int,
    val sku : Int,
    val nome : String,
    val imagemUrl : String,
    val precoAtual : Float,
    val precoAnterior : Float,
    val percentualCompra : Int,
    val classificacao : Float,
    val parcelamento : String


): Serializable