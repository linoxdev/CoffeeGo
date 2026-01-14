package com.alonso.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.navRoot
import com.alonso.search.components.SearchBar
import com.alonso.ui_components.components.AlertDialogError
import com.alonso.ui_components.components.CoffeeGoHeader
import com.alonso.ui_components.components.ContentLoader
import com.alonso.ui_components.components.PrimaryCoffeeCard

@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    val event = viewModel.event.collectAsStateWithLifecycle(SearchEvent.Init).value
    uiState.coffeeToSearch.useDebounce { viewModel.searchCoffeeByName(it) }


    when (event) {
        SearchEvent.Init -> Unit
        SearchEvent.ErrorToAccessData -> {
            AlertDialogError(
                onRetry = { viewModel.closeDialog() }
            )
        }
    }

    Scaffold(
        topBar = {
            Column {
                CoffeeGoHeader(
                    title = "Search",
                    modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 12.dp)
                )
                SearchBar(
                    textChange = { viewModel.setCoffeeToSearch(it) },
                    modifier = Modifier.padding(12.dp),
                    textValue = uiState.coffeeToSearch,
                    autofocus = true,
                    onSearch = {}
                )
            }

        },
        containerColor = CoffeeGoTheme.colors.backgroundHome,
        modifier = modifier
            .fillMaxSize()
    ) { innerPadding ->
        ContentCoffeeList(uiState, innerPadding, appNavigator)
    }
}

@Composable
private fun ContentCoffeeList(
    uiState: SearchState,
    innerPadding: PaddingValues,
    appNavigator: AppNavigator
) {
    if (uiState.isLoading) {
        ContentLoader(modifier = Modifier.padding(innerPadding))
    } else {
        LazyVerticalStaggeredGrid(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            columns = StaggeredGridCells.Fixed(2),
            state = rememberLazyStaggeredGridState(),
        ) {

            items(uiState.coffees, key = { it.id }) { coffee ->
                PrimaryCoffeeCard(
                    modifier = Modifier.padding(8.dp),
                    coffeeName = coffee.name,
                    price = coffee.price.toString(),
                    volume = coffee.volume,
                    qualification = coffee.qualification,
                    onClick = {
                        appNavigator.goTo(
                            AppScreen.Detail(
                                coffeeClicked = uiState.coffees.indexOf(coffee),
                                listCoffee = uiState.coffees
                            )
                        )
                    },
                    imageUrl = coffee.image
                )
            }
            item(span = StaggeredGridItemSpan.FullLine) {
                Spacer(modifier = Modifier.height(100.dp))
            }
        }
    }
}