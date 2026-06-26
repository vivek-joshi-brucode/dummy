package com.example.dummy.repository.baserepo

import com.example.dummy.utils.ApiState
import retrofit2.Response

abstract class BaseRepository {

    suspend fun <T> safeApiCall(
        apiCall: suspend () -> Response<T>
    ): ApiState<T> {
        return try {
            val response = apiCall()
            if (response.isSuccessful && response.body() != null) {
                ApiState.Success(response.body()!!)
            } else {
                ApiState.Error("API error: ${response.code()} ${response.message()}")
            }
        } catch (e: Exception) {
            ApiState.Error("Network error: ${e.message}", e)
        }
    }
}