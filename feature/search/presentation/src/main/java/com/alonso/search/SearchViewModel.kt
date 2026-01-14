package com.alonso.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alonso.domain.SearchUseCase
import com.alonso.domain.getResult
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
class SearchViewModel @Inject constructor(
    val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state: StateFlow<SearchState> = _state.asStateFlow()

    private val _event = MutableSharedFlow<SearchEvent>()
    val event = _event.asSharedFlow()
    fun searchCoffeeByName(value: String = "a") {
        if (value.isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            searchUseCase(value).getResult(
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
                    _event.emit(SearchEvent.ErrorToAccessData)
                }
            )
        }.invokeOnCompletion {
            _state.update { it.copy(isLoading = false) }
        }

    }

    fun setCoffeeToSearch(value: String) {
        _state.update { it.copy(coffeeToSearch = value) }
    }

    fun closeDialog() {
        viewModelScope.launch {
            _event.emit(SearchEvent.Init)
        }
    }
}