package com.alonso.challengecompose.utils

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.produceState
import androidx.compose.ui.platform.LocalContext


@Composable
fun connectivityState(): State<Boolean> {
    val context = LocalContext.current
    return produceState(initialValue = isNetworkConnected(context)) {
        NetworkObserver(context).observeConnectivity().collect { value = it }
    }
}
