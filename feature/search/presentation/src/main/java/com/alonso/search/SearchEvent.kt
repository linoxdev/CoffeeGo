package com.alonso.search

sealed interface SearchEvent {
    object Init : SearchEvent
    object ErrorToAccessData : SearchEvent
}