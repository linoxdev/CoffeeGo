package com.alonso.domain

import java.io.IOException

data class ResultDataEntity<out T, out E : Error>(
    val state: State,
    val data: T? = null,
    val error: E? = null,
) {
    enum class State {
        SUCCESS, ERROR
    }

    companion object {
        fun <T, E : Error> success(data: T): ResultDataEntity<T, E> {
            return ResultDataEntity(State.SUCCESS, data, error = null)
        }

        fun <T, E : Error> error(error: E?): ResultDataEntity<T, E> {
            return ResultDataEntity(State.ERROR, null, error)
        }
    }
}

fun <D, E : Error, R> ResultDataEntity<D, E>.accessDataToMap(map: (D?) -> R): ResultDataEntity<R, E> {
    return when (this.state) {
        ResultDataEntity.State.SUCCESS -> ResultDataEntity.success(map(data))
        ResultDataEntity.State.ERROR -> ResultDataEntity.error(error)
    }
}

inline fun <T, E : Error> ResultDataEntity<T, E>.getResult(
    onSuccess: (T?) -> Unit,
    onError: (E?) -> Unit,
) {
    when (this.state) {
        ResultDataEntity.State.SUCCESS -> {
            onSuccess(this.data)
        }

        ResultDataEntity.State.ERROR -> {
            onError(this.error)
        }
    }
}

sealed interface Error
sealed interface ErrorEntity : Error {

    sealed interface Network : ErrorEntity {
        data object RequestTimeout : Network
        data object PayloadTooLarge : Network
        data class NoInternet(val throwable: IOException) : Network
        data class ServiceError(val key: String, val message: String) : Network
        data object EmptyBodyError : Network
        data class UnknownError(val exception: Exception?, val message: String?) : Network
        data class ClientError(val message: String?) : Network
        data class Unauthorized(val message: String?) : Network
        data class NotFound(val message: String?) : Network
    }

    sealed interface Local : ErrorEntity {
        data object EmptyBodyError : Local
        data class UnknownError(val exception: Exception?) : Local
    }
}