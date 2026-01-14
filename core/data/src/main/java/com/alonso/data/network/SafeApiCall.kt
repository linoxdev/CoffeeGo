package com.alonso.data.network

import com.alonso.domain.ErrorEntity
import com.alonso.domain.ResultDataEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.SocketTimeoutException

suspend inline fun <R : Any> safeApiCall(
    crossinline apiToBeCalled: suspend () -> Response<R>,
): ResultDataEntity<R, ErrorEntity.Network> = withContext(Dispatchers.IO) {
    return@withContext try {
        val response = apiToBeCalled()
        val body = response.body()
        val errorBody = response.errorBody()
        when {
            response.isSuccessful && body != null -> ResultDataEntity.success(body)
            errorBody != null -> {
                val error = when (response.code()) {
                    in 400..499 -> ErrorEntity.Network.ClientError(response.message())
                    else -> ErrorEntity.Network.ServiceError(
                        response.code().toString(),
                        response.message()
                    )
                }
                ResultDataEntity.error(error)
            }

            response.body() == null -> ResultDataEntity.error(ErrorEntity.Network.EmptyBodyError)
            else -> ResultDataEntity.error(
                ErrorEntity.Network.UnknownError(
                    null,
                    response.message().toString()
                )
            )
        }
    }  catch (throwable: IOException) {
        ResultDataEntity.error(
            ErrorEntity.Network.UnknownError(
                throwable,
                throwable.message.toString()
            )
        )
    } catch (e: SocketTimeoutException) {
        ResultDataEntity.error(ErrorEntity.Network.RequestTimeout)
    } catch (e: HttpException) {
        when (e.code()) {
            408 -> ResultDataEntity.error(ErrorEntity.Network.RequestTimeout)
            413 -> ResultDataEntity.error(ErrorEntity.Network.PayloadTooLarge)
            401 -> ResultDataEntity.error(
                ErrorEntity.Network.ServiceError(
                    e.code().toString(),
                    e.message.toString()
                )
            )

            in 500..599 -> ResultDataEntity.error(
                ErrorEntity.Network.ServiceError(
                    e.code().toString(), e.message.toString()
                )
            )

            else -> ResultDataEntity.error(ErrorEntity.Network.Unauthorized(e.message))
        }
    }  catch (throwable: Exception) {
        ResultDataEntity.error(
            ErrorEntity.Network.UnknownError(
                throwable,
                throwable.message.toString()
            )
        )
    }
}