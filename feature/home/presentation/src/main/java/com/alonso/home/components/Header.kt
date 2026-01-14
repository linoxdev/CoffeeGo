package com.alonso.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R

@Composable
fun Header(
    modifier: Modifier = Modifier,
    title: String,
    onSearchClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {

        Text(
            text = title,
            modifier = Modifier,
            style = TextStyle(
                color = CoffeeGoTheme.colors.headerHomeTitle,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(Font(R.font.roboto_flex))
            )
        )
        IconButton(onClick = onSearchClicked) {
            Icon(
                painter = painterResource(R.drawable.ic_search),
                contentDescription = "",
                tint = CoffeeGoTheme.colors.iconTint,
                modifier = Modifier.size(27.dp)
            )
        }

    }


}