package com.uesleiramos.e_commerce.ui.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.infrastructure.event.response.Preco
import com.uesleiramos.e_commerce.infrastructure.event.response.Product
import com.uesleiramos.e_commerce.infrastructure.event.response.ProductsResponse
import com.uesleiramos.e_commerce.infrastructure.event.retrofit.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductViewModel : ViewModel() {

    val listProduct: MutableLiveData<List<Product>> = MutableLiveData()
    val vfLiveDate: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()

    fun init() {
        getProduct()
    }

    fun getProduct() {
        ApiService.service.getProducts().enqueue(object : Callback<ProductsResponse> {
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                if (response.isSuccessful) {
                    val products: MutableList<Product> = mutableListOf()
                    response.body()?.let { ProductsResponse ->
                        for (result in ProductsResponse.produtos) {
                            val preco = Preco(
                                planoPagamento = result.preco.planoPagamento,
                                valorParcela = result.preco.valorParcela,
                                quantidadeMaximaParcelas = result.preco.quantidadeMaximaParcelas,
                                precoAtual = result.preco.precoAtual,
                                precoAnterior = result.preco.precoAnterior,
                                porcentagemDesconto = result.preco.porcentagemDesconto,
                                descontoFormaPagamento = result.preco.descontoFormaPagamento
                            )

                            val product = Product(
                                id = result.id,
                                sku = result.sku,
                                nome = result.nome,
                                disponivel = result.disponivel,
                                descricao = result.descricao,
                                imagemUrl = result.imagemUrl,
                                classificacao = result.classificacao,
                                preco = preco
                            )
                            products.add(product)

                    }
                        listProduct.value = products
                    }
                    vfLiveDate.value = Pair(VIEW_FLIPPER_PRODUCT, null)
                }else if (response.code() == 401) {
                    vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_401)
                } else {
                    vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_400_generic)
                }
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_500_generic)
            }
        })
    }

    companion object {
        private const val VIEW_FLIPPER_PRODUCT = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}