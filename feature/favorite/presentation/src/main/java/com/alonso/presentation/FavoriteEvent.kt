package com.alonso.presentation


sealed interface FavoriteEvent {
    object Init : FavoriteEvent
    object ErrorToAccessData : FavoriteEvent
}