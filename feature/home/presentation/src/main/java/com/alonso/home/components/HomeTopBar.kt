package com.alonso.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alonso.designsystem.R
import com.alonso.home.CategoryOption

@Composable
internal fun HomeTopBar(
    modifier: Modifier = Modifier,
    categories: List<CategoryOption>,
    selectedCategory: String,
    showBanner: Boolean,
    onClick: (CategoryOption) -> Unit,
    onSearchClicked: () -> Unit

) {
    Column(modifier = modifier.fillMaxWidth()) {
        Header(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, top = 0.dp, bottom = 4.dp),
            title = "Coffee Go",
            onSearchClicked = onSearchClicked
        )
        BannerImage(
            modifier = Modifier.padding(start = 12.dp, end = 12.dp, bottom = 16.dp),
            show = showBanner,
            title = "YOUR DAILY \nCOFFEE RITUAL STARTS HERE",
            image = R.drawable.cove_banner_coffe
        )
        LazyRow(
            modifier = Modifier
                .padding(bottom = 8.dp)
        ) {
            items(categories) {
                CategoryOption(
                    text = it.name,
                    isSelected = selectedCategory == it.id,
                    onClick = { onClick(it) }
                )
            }
        }
    }
}
