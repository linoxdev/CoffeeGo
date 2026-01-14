package com.alonso.ui_components.components


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
inline fun ScaffoldColumn(
    modifier: Modifier = Modifier,
    modifierChild: Modifier = Modifier,
    containerColor: Color,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    crossinline content: @Composable ColumnScope.() -> Unit,
) {

    Scaffold(
        modifier = modifier,
        containerColor = containerColor,


        ) {
        Column(
            modifier = modifierChild
                .padding(it)
                .fillMaxSize(),
            verticalArrangement = verticalArrangement,
            horizontalAlignment = horizontalAlignment
        ) {
            content.invoke(this)
        }

    }
}