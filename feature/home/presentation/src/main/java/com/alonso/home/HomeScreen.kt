package com.alonso.home

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R
import com.alonso.home.components.HomeTopBar
import com.alonso.home.components.LoadCoffeeList
import com.alonso.home.components.SecondaryCoffeeCard
import com.alonso.home.utils.RowCoffeeSection
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.navigation.CoffeeItem
import com.alonso.navigation.navRoot
import com.alonso.ui_components.components.AlertDialogError
import com.alonso.ui_components.components.PrimaryCoffeeCard

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    appNavigator: AppNavigator = navRoot,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsStateWithLifecycle().value
    val event = viewModel.event.collectAsStateWithLifecycle(HomeEvent.Init).value
    val lazyStaggeredGridState = rememberLazyStaggeredGridState()

    when (event) {
        HomeEvent.Init -> Unit
        HomeEvent.ErrorToAccessData -> {
            AlertDialogError(
                onRetry = { viewModel.closeDialog() }
            )
        }
    }
    BackHandler {
        appNavigator.clearBackStack()
    }
    Scaffold(
        modifier = modifier.fillMaxSize(),
        containerColor = CoffeeGoTheme.colors.backgroundHome,
        topBar = {
            HomeTopBar(
                categories = uiState.categories,
                selectedCategory = uiState.selectedCategory,
                showBanner = lazyStaggeredGridState.isScrollingDown().value.not(),
                onClick = { viewModel.onSelectCategory(it.id) },
                onSearchClicked = { appNavigator.goTo(AppScreen.Search) }
            )
        }
    ) { innerPadding ->

        if (uiState.isLoading) {
            LoadCoffeeList(
                modifier = Modifier
                    .padding(innerPadding)
                    .fillMaxSize(), lazyGridState = rememberLazyGridState()
            )
        } else {

            ContentCoffeeList(
                innerPadding = innerPadding,
                state = lazyStaggeredGridState,
                coffeeList = uiState.coffeeList,
                favorites = uiState.favorites,
                appNavigator = appNavigator
            )
        }
    }
}

@Composable
private fun ContentCoffeeList(
    innerPadding: PaddingValues,
    state: LazyStaggeredGridState,
    coffeeList: List<CoffeeItem>,
    favorites: List<CoffeeItem>,
    appNavigator: AppNavigator

) {
    LazyVerticalStaggeredGrid(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding),
        columns = StaggeredGridCells.Fixed(2),
        state = state,
    ) {
        RowCoffeeSection(
            titleSection = "Favorites",
            adapter = {
                items(favorites, key = { it.id }) { coffee ->
                    SecondaryCoffeeCard(
                        coffeeName = coffee.name,
                        imageUrl = coffee.image,
                        onClick = {
                            appNavigator.goTo(
                                AppScreen.Detail(
                                    coffeeClicked = favorites.indexOf(coffee),
                                    listCoffee = favorites
                                )
                            )
                        }
                    )

                }
            }
        )
        item(span = StaggeredGridItemSpan.FullLine) {
            Text(
                "Our products",
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                style = TextStyle(
                    color = CoffeeGoTheme.colors.textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_flex))
                )
            )
        }
        items(coffeeList, key = { it.id }) { coffee ->
            PrimaryCoffeeCard(
                modifier = Modifier.padding(8.dp),
                coffeeName = coffee.name,
                price = coffee.price.toString(),
                volume = coffee.volume,
                qualification = coffee.qualification,
                onClick = {
                    appNavigator.goTo(
                        AppScreen.Detail(
                            coffeeClicked = coffeeList.indexOf(coffee),
                            listCoffee = coffeeList
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

@Composable
private fun LazyStaggeredGridState.isScrollingDown() = remember {
    derivedStateOf {
        firstVisibleItemIndex > 0 ||
                firstVisibleItemScrollOffset > 0
    }
}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun HomeScreenPreview() {
    HomeScreen()
}
