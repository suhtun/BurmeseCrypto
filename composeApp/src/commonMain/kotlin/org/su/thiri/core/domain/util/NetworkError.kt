package com.su.coinproject.core.domain.util

enum class NetworkError: Error {
    REQUEST_TIMEOUT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    SERVER_ERROR,
    JSON_CONVERT,
    SERIALIZATION,
    COIN_NOT_FOUND,
    VALIDATION_ERROR,
    EMPTY_DATA,
    UNKNOWN,
}