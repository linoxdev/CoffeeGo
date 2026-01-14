package com.alonso.home

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.alonso.domain.getResult
import com.alonso.domain.usecase.HomeUseCase
import com.alonso.ui_components.base.BaseViewModel
import com.alonso.ui_components.base.Loader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    loader: Loader,
    private val homeUseCase: HomeUseCase
) : BaseViewModel(loader) {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState = _uiState.asStateFlow()

    private val _event = MutableSharedFlow<HomeEvent>()
    val event = _event.asSharedFlow()

    init {
        getCoffeeByCategory()
    }

    private fun getCoffeeByCategory(categoryId: String = "all") {
        Log.d("PRINT", "getCoffeeByCategory: $categoryId")
        launchIO(
            task = {
                _uiState.update { it.copy(isLoading = true) }
                delay(1000)
                homeUseCase(categoryId).getResult(
                    onSuccess = { data ->
                        data?.let { result ->
                            _uiState.update {
                                it.copy(
                                    coffeeList = result.toCoffeeItem(),
                                    favorites = result
                                        .filter { coffee -> coffee.favorite }.toCoffeeItem()
                                )
                            }
                        }
                    },
                    onError = { _event.emit(HomeEvent.ErrorToAccessData) }
                )
            }, onCompletion = {
                _uiState.update { it.copy(isLoading = false) }
            }
        )
    }

    fun onSelectCategory(categoryId: String) {
        getCoffeeByCategory(categoryId = categoryId)
        _uiState.update { it.copy(selectedCategory = categoryId) }
    }

    fun closeDialog() {
        viewModelScope.launch { _event.emit(HomeEvent.Init) }
    }
}
