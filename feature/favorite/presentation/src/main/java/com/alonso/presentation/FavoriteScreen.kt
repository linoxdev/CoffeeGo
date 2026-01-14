package com.alonso.presentation

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
import com.alonso.ui_components.components.AlertDialogError
import com.alonso.ui_components.components.CoffeeGoHeader
import com.alonso.ui_components.components.ContentLoader
import com.alonso.ui_components.components.PrimaryCoffeeCard


@Composable
fun FavoriteScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot,
    viewModel: FavoriteViewModel = hiltViewModel()
) {
    val uiState = viewModel.state.collectAsStateWithLifecycle().value
    val event = viewModel.event.collectAsStateWithLifecycle(FavoriteEvent.Init).value

    when (event) {
        FavoriteEvent.Init -> Unit
        FavoriteEvent.ErrorToAccessData -> {
            AlertDialogError(
                onRetry = { viewModel.closeDialog() }
            )
        }
    }

    Scaffold(
        containerColor = CoffeeGoTheme.colors.backgroundHome,
        modifier = modifier.fillMaxSize(),
        topBar = { CoffeeGoHeader(title = "Favorites", modifier = Modifier.padding(12.dp)) }
    ) { innerPadding ->
        ContentCoffeeList(uiState, innerPadding, appNavigator)
    }
}

@Composable
private fun ContentCoffeeList(
    uiState: FavoriteState,
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