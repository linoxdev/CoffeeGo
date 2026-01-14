package com.alonso.ui_components.base

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

abstract class BaseViewModel(
    private val loader: Loader? = null
) : ViewModel() {

    protected fun launchLoad(
        block: suspend CoroutineScope.() -> Unit
    ) {
        loader?.start()
        viewModelScope.launch(Dispatchers.IO) {
            try {
                block()
            } catch (e: Throwable) {
                Log.d("TAG", "launchLoad: ${e.message}")
            } finally {
                loader?.stop()
            }
        }.invokeOnCompletion { loader?.stop() }

    }

    protected fun launchIO(
        task: suspend CoroutineScope.() -> Unit,
        onCompletion: (Throwable?) -> Unit = {}
    ) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                task()
            } catch (e: Throwable) {
                Log.d("TAG", "launchIO: ${e.message}")
            }
        }.invokeOnCompletion {
            onCompletion(it)
        }

    }

    companion object {
        private const val TAG = "BaseViewModel"
    }


}