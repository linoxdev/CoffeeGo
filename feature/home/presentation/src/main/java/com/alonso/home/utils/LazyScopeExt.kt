package com.alonso.home.utils

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridScope
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R


fun LazyStaggeredGridScope.RowCoffeeSection(
    modifier: Modifier = Modifier,
    titleSection: String,
    adapter: LazyListScope.() -> Unit,
) {
    item(span = StaggeredGridItemSpan.FullLine) {
        Column(
            modifier = modifier
                .fillMaxWidth()
        ) {
            Text(
                titleSection,
                modifier = Modifier.padding(horizontal = 12.dp, vertical = 8.dp),
                style = TextStyle(
                    color = CoffeeGoTheme.colors.textColor,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = FontFamily(Font(R.font.roboto_flex))
                )
            )
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
            ) { adapter() }
        }

    }
}