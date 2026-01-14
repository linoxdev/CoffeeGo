package com.alonso.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme


@Composable
fun CategoryOption(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean = false,
    onClick: () -> Unit = {}
) {
    val backgroundColor = if (isSelected) CoffeeGoTheme.colors.optionCategoryBackgroundEnabled
    else CoffeeGoTheme.colors.optionCategoryBackgroundDisabled

    val borderColor = if (isSelected) CoffeeGoTheme.colors.optionCategoryBorderEnabled
    else CoffeeGoTheme.colors.optionCategoryBorderDisabled

    val textColor = if (isSelected) CoffeeGoTheme.colors.optionCategoryTextEnabled else
        CoffeeGoTheme.colors.optionCategoryTextDisabled

    Box(
        Modifier
            .padding(4.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(24.dp)
            )
    ) {
        Text(
            modifier = modifier
                .padding(2.dp)
                .clickable(
                    role = Role.Button,
                    onClick = onClick,
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                )
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .border(
                    width = if (isSelected) (1.2).dp else 1.dp,
                    color = borderColor,
                    shape = RoundedCornerShape(24.dp)
                )
                .padding(horizontal = 16.dp, vertical = 8.dp),
            text = text,
            style = CoffeeGoTheme.typography.bodyMedium.copy(
                color = textColor,
                fontSize = 12.sp,
                fontWeight = if (isSelected) FontWeight.Medium else FontWeight.Normal
            )
        )
    }

}


@Preview(showBackground = true)
@Composable
private fun CategoryOptionPreview() {
    CategoryOption(
        text = "Recommendation",
        isSelected = false,
        onClick = {}
    )

}


@Preview(showBackground = true)
@Composable
private fun CategoryOptionSelectedPreview() {
    CategoryOption(
        text = "Coffee",
        isSelected = true,
        onClick = {}
    )

}