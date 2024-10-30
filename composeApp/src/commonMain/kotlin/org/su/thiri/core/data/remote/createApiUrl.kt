package com.su.coinproject.core.data.remote


fun createApiUrl(url: String): String {
    //todo: wip not sure can not configure baseUrl so use mock api instead
    val baseUrl = "https://api.coinranking.com/v2/"
    return when {
        url.contains(baseUrl) -> baseUrl
        url.startsWith("/") -> baseUrl + url.drop(1)
        else -> baseUrl
    }
}