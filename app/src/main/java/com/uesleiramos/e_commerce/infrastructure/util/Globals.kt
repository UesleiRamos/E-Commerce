package com.uesleiramos.e_commerce.infrastructure.util

object Globals {
    enum class URLs constructor(val url: String) {
        URL_BASE("http://www.mocky.io/v2/")
    }

    fun getUrlBase() = URLs.URL_BASE.url
}