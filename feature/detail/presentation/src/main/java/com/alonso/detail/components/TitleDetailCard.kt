package com.alonso.detail.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme

@Composable
fun TitleDetailCard(
    modifier: Modifier = Modifier,
    name: String,
    volume: String,
    price: Double,
    category: String
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.Top
    ) {
        ProductInfoSection(
            name = name,
            volume = volume,
            modifier = Modifier.weight(0.7f)
        )

        PriceSection(
            price = price,
            category = category,
            modifier = Modifier.weight(0.3f)
        )
    }
}

@Composable
private fun ProductInfoSection(
    name: String,
    volume: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        ProductName(name = name)
        VolumeInfo(volume = volume)
    }
}

@Composable
private fun ProductName(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = CoffeeGoTheme.typography.headlineMedium,
        maxLines = 2,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
private fun VolumeInfo(
    volume: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = volume,
        style = MaterialTheme.typography.bodyMedium.copy(
            color = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        modifier = modifier
    )
}

@Composable
private fun PriceSection(
    price: Double,
    category: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        PriceText(price = price)
        CategoryChip(category = category)
    }
}

@Composable
private fun PriceText(
    price: Double,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$ ${"%.2f".format(price)}",
        style = CoffeeGoTheme.typography.headlineMedium.copy(
            fontWeight = FontWeight.Bold
        ),
        color = Color.Black,
        modifier = modifier
    )
}

@Composable
private fun CategoryChip(
    category: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = category,
        style = TextStyle(
            color = Color(0xFF897A71),
            fontWeight = FontWeight.Medium,
            fontSize = 12.sp
        ),
        modifier = modifier
            .background(
                color = Color(0xFFEFE6E1),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(horizontal = 12.dp, vertical = 6.dp)
    )
}