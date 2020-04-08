package com.uesleiramos.e_commerce.infrastructure.event.response

data class Padrao(

	val sku: Int,
	val nome: String,
	val disponivel: Boolean,
	val marketplace: Marketplace,
	val preco: Preco,
	val imagens: List<Imagens>,
	val servicos: List<Servicos>
)