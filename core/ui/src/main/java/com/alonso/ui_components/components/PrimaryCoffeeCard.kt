package com.alonso.ui_components.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R

@Composable
fun PrimaryCoffeeCard(
    modifier: Modifier = Modifier,
    modifierImg: Modifier = Modifier,
    coffeeName: String,
    volume: String,
    price: String,
    qualification: Double,
    imageUrl: String,
    onClick: () -> Unit = {}
) {

    Box(
        modifier = modifier
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            )
            .width(200.dp)
            .wrapContentHeight()
            .border(
                width = 1.dp,
                color = CoffeeGoTheme.colors.coffeeCardBorder,
                shape = RoundedCornerShape(18.dp)
            )
            .background(
                color = CoffeeGoTheme.colors.coffeeCardBackgroundPrimary,
                shape = RoundedCornerShape(18.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            CoffeeImageContainer(
                modifier = modifierImg,
                imageUrl = imageUrl
            )
            Spacer(modifier = Modifier.height(14.dp))
            CoffeeInfo(coffeeName = coffeeName, volume = volume, price = price)

        }
        CoffeeRating(
            modifier = Modifier.align(Alignment.BottomEnd),
            valueColor = CoffeeGoTheme.colors.textColor,
            value = qualification.toString()
        )
    }
}


@Composable
private fun CoffeeImageContainer(
    modifier: Modifier = Modifier,
    imageUrl: String,
) {
    Box(
        modifier = modifier

            .fillMaxWidth()
            .height(150.dp)
            .background(
                color = CoffeeGoTheme.colors.coffeeCardBackgroundSecondary,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(10.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        LoadImage(
            url = imageUrl,
            modifier = Modifier
                .wrapContentHeight()
                .width(100.dp)
        )
    }
}

@Composable
private fun CoffeeInfo(
    coffeeName: String,
    volume: String,
    price: String
) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        text = coffeeName,
        overflow = TextOverflow.Ellipsis,
        maxLines = 2,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_flex)),
            fontWeight = FontWeight.Medium,
            color = CoffeeGoTheme.colors.textColor,
            fontSize = 17.sp
        )
    )
    Spacer(modifier = Modifier.height(2.dp))
    Text(
        text = volume,
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_flex)),
            color = CoffeeGoTheme.colors.grayCommon,
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )
    )
    Spacer(modifier = Modifier.height(4.dp))
    Text(
        text = "$ $price",
        style = TextStyle(
            fontFamily = FontFamily(Font(R.font.roboto_flex)),
            color = CoffeeGoTheme.colors.textColor,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    )
}

/**
 * Preview del componente CoffeeCard
 */
@Preview(showBackground = true)
@Composable
private fun CoffeeCardPreview() {
    PrimaryCoffeeCard(
        coffeeName = "Vietnamese Coconut Coffee",
        price = "11",
        volume = "100 ml",
        imageUrl = "https://png.pngtree.com/png-clipart/20240220/original/pngtree-latte-macchiato-coffee-glass-png-image_14366823.png",
        qualification = 4.5
    )
}