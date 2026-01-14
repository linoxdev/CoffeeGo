package com.alonso.home

sealed interface HomeEvent {
    object Init : HomeEvent
    object ErrorToAccessData : HomeEvent
}