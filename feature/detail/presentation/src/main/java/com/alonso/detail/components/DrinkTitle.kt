package com.alonso.detail.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.alonso.designsystem.CoffeeGoTheme

@Composable
fun DrinkTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = CoffeeGoTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun DrinkTitlePreview() {
    DrinkTitle(title = "Strawberry Cobbler Drink")

}