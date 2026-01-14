package com.alonso.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R
import com.alonso.ui_components.components.LoadImage

@Composable
fun SecondaryCoffeeCard(
    modifier: Modifier = Modifier,
    coffeeName: String,
    imageUrl: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier.padding(6.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                )
                .background(
                    color = CoffeeGoTheme.colors.coffeeCardBackgroundSecondary,
                    shape = RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            LoadImage(
                url = imageUrl,
                modifier = Modifier
                    .wrapContentHeight()
                    .size(70.dp)
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            coffeeName,
            modifier = Modifier.width(100.dp),
            maxLines = 2,
            textAlign = TextAlign.Center,
            overflow = TextOverflow.Ellipsis,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.roboto_flex)),
                fontWeight = FontWeight.Medium,
                color = CoffeeGoTheme.colors.textColor,
                fontSize = 12.sp
            )
        )
    }
}

/**
 * Preview del componente CoffeeCard
 */
@Preview(showBackground = true)
@Composable
private fun CoffeeCardPreview() {
    SecondaryCoffeeCard(
        modifier = Modifier,
        coffeeName = "Vietnamese Coconut Coffee",
        imageUrl = "https://png.pngtree.com/png-clipart/20240220/original/pngtree-latte-macchiato-coffee-glass-png-image_14366823.png",
        {}
    )

}