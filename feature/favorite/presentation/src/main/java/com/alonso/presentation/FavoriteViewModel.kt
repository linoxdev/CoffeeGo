package com.alonso.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.domain.getResult
import com.alonso.domain.usecase.FavoriteUseCase
import com.alonso.navigation.CoffeeItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class FavoriteViewModel @Inject constructor(
    val searchUseCase: FavoriteUseCase
) : ViewModel() {
    private val _state = MutableStateFlow(FavoriteState())
    val state: StateFlow<FavoriteState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<FavoriteEvent>()
    val event = _event.asSharedFlow()

    init {
        getFavoriteCoffees()
    }

    private fun getFavoriteCoffees() {
        viewModelScope.launch(Dispatchers.IO) {
            searchUseCase().getResult(
                onSuccess = {
                    it?.let { result ->
                        _state.update { searchState ->
                            searchState.copy(coffees = result.map { coffeeEntity ->
                                CoffeeItem(
                                    id = coffeeEntity.id,
                                    name = coffeeEntity.name,
                                    price = coffeeEntity.price,
                                    description = coffeeEntity.description,
                                    image = coffeeEntity.image,
                                    volume = coffeeEntity.volume,
                                    qualification = coffeeEntity.ranking,
                                    category = coffeeEntity.category,
                                    favorite = coffeeEntity.favorite
                                )
                            })
                        }
                    }
                },
                onError = {
                    _event.emit(FavoriteEvent.ErrorToAccessData)
                }
            )
        }
    }

    fun closeDialog() {
        viewModelScope.launch {
            _event.emit(FavoriteEvent.Init)
        }
    }
}