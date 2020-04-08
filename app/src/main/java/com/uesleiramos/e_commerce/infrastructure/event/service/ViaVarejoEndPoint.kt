package com.uesleiramos.e_commerce.infrastructure.event.service

import com.uesleiramos.e_commerce.infrastructure.event.response.ProductDetailResponse
import com.uesleiramos.e_commerce.infrastructure.event.response.ProductsResponse
import com.uesleiramos.e_commerce.infrastructure.event.response.QuemViu
import retrofit2.Call
import retrofit2.http.GET

interface ViaVarejoEndPoint {

    @GET("5d1b4f0f34000074000006dd")
    fun getProducts(): Call<ProductsResponse>

    @GET("5d1b4fd23400004c000006e1")
    fun getProductDetails(): Call<ProductDetailResponse>

    @GET("5d1b507634000054000006ed")
    fun getWhoSawIt(): Call<List<QuemViu>>
}