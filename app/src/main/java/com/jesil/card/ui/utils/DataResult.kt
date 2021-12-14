package com.jesil.card.ui.utils

sealed class DataResult<out T> {
    data class Success<T>(val data : List<T>?) : DataResult<T>()
    data class Failure(val throwable: Throwable?): DataResult<Nothing>()
    object Loading : DataResult<Nothing>()
}