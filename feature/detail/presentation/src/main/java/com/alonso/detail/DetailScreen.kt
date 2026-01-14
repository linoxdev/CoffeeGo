package com.alonso.detail

import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.detail.components.CoffeeListSlider
import com.alonso.detail.components.CofferDetailsCard
import com.alonso.navigation.CoffeeItem
import com.alonso.ui_components.components.ScaffoldColumn


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    listCoffee: List<CoffeeItem>,
    coffeeClicked: Int = 0,
) {

    val scrollState = rememberScrollState()
    val currentCoffee = remember(listCoffee, coffeeClicked) {
        mutableStateOf(
            listCoffee.getOrNull(coffeeClicked) ?: listCoffee.firstOrNull()
        )
    }


    ScaffoldColumn(
        modifier = Modifier.fillMaxSize(),
        containerColor = CoffeeGoTheme.colors.backgroundDetails,
        modifierChild = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        CoffeeListSlider(
            modifier = Modifier
                .zIndex(3f)
                .offset(y = (-60).dp),
            coffeeList = listCoffee,
            coffeeClicked = coffeeClicked,
            currentPage = { currentCoffee.value = it },
        )

        CofferDetailsCard(
            modifier = Modifier
                .padding(horizontal = 18.dp)
                .zIndex(1f)
                .offset(y = (-70).dp),
            drink = currentCoffee.value,
        )
    }

}


@OptIn(ExperimentalSharedTransitionApi::class)
@Preview(showBackground = true, name = "Full Screen")
@Composable
private fun DetailScreenPreview() {
    DetailScreen(
        coffeeClicked = 0,
        listCoffee = listOf(
            CoffeeItem(
                id = "",
                name = "Espresso",
                price = 2.50,
                description = "Un shot concentrado de café con un sabor intenso y una crema rica.",
                image = "https://static.vecteezy.com/system/resources/previews/048/095/748/non_2x/shot-of-rich-espresso-with-a-creamy-top-png.png",
                qualification = 5.0,
                category = "Coffee",
                volume = "32ml",
                favorite = false
            )
        )
    )
}

