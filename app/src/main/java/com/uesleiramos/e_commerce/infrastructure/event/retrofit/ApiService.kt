package com.uesleiramos.e_commerce.infrastructure.event.retrofit

import com.uesleiramos.e_commerce.infrastructure.event.service.ViaVarejoEndPoint
import com.uesleiramos.e_commerce.infrastructure.util.Globals
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiService {
    private fun iniRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(Globals.getUrlBase())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val service = iniRetrofit().create(ViaVarejoEndPoint::class.java)
}