package com.example.roomretrofitmvvm.responses

sealed class ResponseResult<T>(
    val data: T? = null,
    val error: String? = null
) {
    class Loading<T> : ResponseResult<T>()
    class Success<T>(data: T? = null) : ResponseResult<T>(data = data)
    class Error<T>(errorMessage: String) : ResponseResult<T>(error = errorMessage)
}