package com.igorgiroti.weathertracker.presentation.ui.state


sealed interface SearchState<out T> {
    object Initial : SearchState<Nothing>
    object Loading : SearchState<Nothing>
    data class Success<out R>(val data: R) : SearchState<R>
}