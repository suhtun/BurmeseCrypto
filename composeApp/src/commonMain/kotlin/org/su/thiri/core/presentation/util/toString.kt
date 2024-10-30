package com.su.coinproject.core.presentation.util

import com.su.coinproject.core.domain.util.NetworkError

fun NetworkError.toString(): String {
    val resId = when(this) {
        NetworkError.REQUEST_TIMEOUT -> "The request timed out."
        NetworkError.TOO_MANY_REQUESTS -> "Oops, it seems like your quota is exceeded."
        NetworkError.NO_INTERNET -> "Could not connect to server, please check your internet connection."
        NetworkError.SERVER_ERROR -> "Something went wrong. Please try again later."
        NetworkError.SERIALIZATION -> "Could not serialize or deserialize data."
        NetworkError.UNKNOWN -> "Something went wrong. Please try again later."
        NetworkError.JSON_CONVERT -> "Could not serialize or deserialize data."
        NetworkError.COIN_NOT_FOUND -> "The coin could not be found. Try another coin."
        NetworkError.VALIDATION_ERROR ->"The request could not be validated. The response should provide more details."
        NetworkError.EMPTY_DATA -> "No data."
    }
    return "No data."
}