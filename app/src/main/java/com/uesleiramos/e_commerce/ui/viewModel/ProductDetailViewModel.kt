package com.uesleiramos.e_commerce.ui.viewModel

import android.text.SpannableString
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.uesleiramos.e_commerce.R
import com.uesleiramos.e_commerce.infrastructure.event.response.ProductDetailResponse
import com.uesleiramos.e_commerce.infrastructure.event.response.QuemViu
import com.uesleiramos.e_commerce.infrastructure.event.retrofit.ApiService
import com.uesleiramos.e_commerce.util.toMoney
import com.uesleiramos.e_commerce.util.withTypeface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProductDetailViewModel : ViewModel() {

    val vfLiveDate: MutableLiveData<Pair<Int, Int?>> = MutableLiveData()
    val listQuemViu: MutableLiveData<List<QuemViu>> = MutableLiveData()

    val productDetail: MutableLiveData<ProductDetailResponse> = MutableLiveData()
    val title: MutableLiveData<String> = MutableLiveData()
    val desc: MutableLiveData<String> = MutableLiveData()
    val oldPrice: MutableLiveData<SpannableString> = MutableLiveData()
    val current_price: MutableLiveData<String> = MutableLiveData()
    val installment: MutableLiveData<String> = MutableLiveData()

    fun getProduct(){
        ApiService.service.getProductDetails().enqueue(object : Callback<ProductDetailResponse> {
            override fun onResponse(
                call: Call<ProductDetailResponse>,
                response: Response<ProductDetailResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        products(it)
                    }
                    vfLiveDate.value = Pair(VIEW_FLIPPER_DETAIL, null)
                } else if (response.code() == 401) {
                    vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_401)
                } else {
                    vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_400_generic)
                }
            }

            override fun onFailure(call: Call<ProductDetailResponse>, t: Throwable) {
                vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_500_generic)
            }
        })

        ApiService.service.getWhoSawIt().enqueue(object : Callback<List<QuemViu>> {
            override fun onResponse(
                call: Call<List<QuemViu>>,
                response: Response<List<QuemViu>>
            ) {
                if (response.isSuccessful) {
                    val listquemviu: MutableList<QuemViu> = mutableListOf()
                    response.body()?.let { quemviu ->
                        for (result in quemviu) {
                            val _quemviu = QuemViu(
                                id = result.id,
                                sku = result.sku,
                                nome = result.nome,
                                imagemUrl = result.imagemUrl,
                                precoAtual = result.precoAtual,
                                precoAnterior = result.precoAnterior,
                                percentualCompra = result.percentualCompra,
                                classificacao = result.classificacao,
                                parcelamento = result.parcelamento
                            )
                            listquemviu.add(_quemviu)
                        }
                        listQuemViu.value = listquemviu
                    }
                }
            }

            override fun onFailure(call: Call<List<QuemViu>>, t: Throwable) {
                vfLiveDate.value = Pair(VIEW_FLIPPER_ERROR, R.string.erro_500_generic)
            }
        })
    }

    fun products(detail: ProductDetailResponse) {
        productDetail.value = ProductDetailResponse(
            id = detail.id,
            nome = detail.nome,
            descricao = detail.descricao,
            retiraEmLoja = detail.retiraEmLoja,
            categorias = detail.categorias,
            maisInformacoes = detail.maisInformacoes,
            marca = detail.marca,
            modelo = detail.modelo,
            urlVideo = detail.urlVideo
        )
    }

    fun fillObject(detail: ProductDetailResponse) {
        title.value = detail.nome
        desc.value = detail.descricao
        oldPrice.value = detail.modelo.padrao.preco.precoAnterior.toMoney(true).withTypeface()
        current_price.value = detail.modelo.padrao.preco.precoAtual.toMoney(true)
        installment.value = detail.modelo.padrao.preco.planoPagamento
    }

    companion object {
        private const val VIEW_FLIPPER_DETAIL = 1
        private const val VIEW_FLIPPER_ERROR = 2
    }
}