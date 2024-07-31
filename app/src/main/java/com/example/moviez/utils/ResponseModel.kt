package com.example.moviez.utils

sealed class ResponseModel<out T> {
    data class Success<out R>(val data: R) : ResponseModel<R>()
    data class Error(val errorMsg: String) : ResponseModel<Nothing>()
    data object Loading : ResponseModel<Nothing>()
}
