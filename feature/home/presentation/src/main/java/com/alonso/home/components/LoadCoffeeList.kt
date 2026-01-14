package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyGridState
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.alonso.ui_components.components.Shimmer

@Composable
fun LoadCoffeeList(
    modifier: Modifier = Modifier,
    lazyGridState: LazyGridState
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(end = 12.dp),
        state = lazyGridState,
    ) {

        item(span = { GridItemSpan(2) }) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Shimmer(
                    modifier = Modifier
                        .padding(vertical = 8.dp, horizontal = 12.dp)
                        .height(25.dp)
                        .width(100.dp)
                )

                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()

                ) {
                    items(10) {
                        Shimmer(
                            modifier = Modifier
                                .padding(horizontal = 6.dp, vertical = 8.dp)
                                .size(120.dp)
                                .clip(RoundedCornerShape(16.dp))
                        )
                    }
                }
            }

        }

        items(10) {
            Shimmer(
                modifier = Modifier
                    .width(210.dp)
                    .padding(start = 12.dp, top = 12.dp)
                    .height(215.dp)
                    .clip(shape = RoundedCornerShape(18.dp))
            )
        }
    }
}