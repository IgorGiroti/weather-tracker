package com.igorgiroti.weathertracker.utils

sealed interface ResponseStatus<out T> {
    object Loading : ResponseStatus<Nothing>
    data class Success<out R>(val data: R) : ResponseStatus<R>
    data class Error(val error: Throwable) : ResponseStatus<Nothing>
}
