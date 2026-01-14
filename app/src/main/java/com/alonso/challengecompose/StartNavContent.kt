package com.alonso.challengecompose

import androidx.compose.animation.ContentTransform
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.alonso.challengecompose.ui.theme.AppTheme
import com.alonso.challengecompose.utils.connectivityState
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.detail.DetailScreen
import com.alonso.navigation.AppNavigator
import com.alonso.navigation.AppScreen
import com.alonso.presentation.DashboardScreen
import com.alonso.search.SearchScreen
import com.alonso.splash.SplashScreen
import com.alonso.ui_components.base.Loader
import com.alonso.ui_components.base.ThemeViewModel
import com.alonso.ui_components.components.LoaderModal
import com.alonso.ui_components.components.NetworkMessageBar

@Composable
fun StartNavContent(
    composeNavigator: AppNavigator,
    loader: Loader,
    sharedViewModel: ThemeViewModel = hiltViewModel()
) {
    AppTheme(
        appNavigator = composeNavigator,
        themeViewModel = sharedViewModel
    ) {
        val connection by connectivityState()
        LoaderModal(loader.loading.collectAsState().value)
        val backStack = rememberNavBackStack(AppScreen.Splash)
        LaunchedEffect(Unit) { composeNavigator.initialize(backStack) }
        NavRootDisplay(connection, backStack, composeNavigator)

    }
}

@Composable
private fun NavRootDisplay(
    connection: Boolean,
    backStack: NavBackStack<NavKey>,
    composeNavigator: AppNavigator
) {
    Scaffold(
        containerColor = CoffeeGoTheme.colors.backgroundHome,
        topBar = {
            NetworkMessageBar(
                isConnected = connection,
                modifier = Modifier.statusBarsPadding()
            )
        }
    ) { innerPadding ->
        NavDisplay(
            backStack = backStack,
            onBack = { composeNavigator.popBack() },
            entryProvider = entryProvider {
                entry<AppScreen.Dashboard> {
                    DashboardScreen(
                        modifier = Modifier.padding(
                            top = innerPadding.calculateTopPadding()
                        ),
                    )
                }
                entry<AppScreen.Splash> {
                    SplashScreen()
                }
                entry<AppScreen.Detail> {
                    DetailScreen(
                        listCoffee = it.listCoffee,
                        coffeeClicked = it.coffeeClicked
                    )
                }
                entry<AppScreen.Search> {
                    SearchScreen(modifier = Modifier.padding(top = innerPadding.calculateTopPadding()))
                }
            },
            transitionSpec = {
                ContentTransform(
                    slideInHorizontally(initialOffsetX = { it }),
                    slideOutHorizontally(targetOffsetX = { -it })
                )
            },
            popTransitionSpec = {
                ContentTransform(
                    slideInHorizontally(initialOffsetX = { -it }),
                    slideOutHorizontally(targetOffsetX = { it })
                )
            }

        )
    }
}