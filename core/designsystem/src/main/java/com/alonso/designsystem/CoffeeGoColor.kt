package com.alonso.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

@Immutable
data class CustomColors(
    val content: Color,
    val backgroundSplash: Color,
    val textIconSplash: Color,
    val backgroundHome: Color,
    val backgroundDetails: Color,
    val textColor: Color,
    val coffeeCardBackgroundPrimary: Color,
    val coffeeCardBackgroundSecondary: Color,
    val coffeeCardBorder: Color,
    val optionCategoryBorderEnabled: Color,
    val optionCategoryBackgroundEnabled: Color,
    val optionCategoryTextEnabled: Color,
    val optionCategoryBorderDisabled: Color,
    val optionCategoryBackgroundDisabled: Color,
    val optionCategoryTextDisabled: Color,
    val headerHomeTitle: Color,
    val headerHomeSubtitle: Color,
    val grayCommon: Color,
    val backgroundNetworkDisconnected: Color,
    val backgroundSearchBar: Color,
    val iconTint: Color,
    val brush: Brush,
    val navBarItemSelected: Color,
    val navBarItemUnselected: Color,
    val placeholderText:Color
)

val LightColors = CustomColors(
    backgroundSplash = Color(0xFFF9F4EA),
    textIconSplash = Color(0xFF5A270D),

    content = Color.Blue,
    backgroundDetails = Color(0xFFDECFC8),
    backgroundHome = Color(0xFFF9F4EA),
    textColor = Color.Black,
    coffeeCardBackgroundPrimary = Color(0xFFF9F4EA),
    coffeeCardBackgroundSecondary = Color(0xFFE6D3C7),
    coffeeCardBorder = Color(0xFFECE6E2),

    optionCategoryBorderEnabled = Color.White,
    optionCategoryBackgroundEnabled = Color(0xFF7d4532),
    optionCategoryTextEnabled = Color.White,

    optionCategoryBorderDisabled = Color(0xFFE0E0E0),
    optionCategoryBackgroundDisabled = Color.Transparent,
    optionCategoryTextDisabled = Color(0xFF534D46),

    headerHomeTitle = Color(0xFF2D231B),
    headerHomeSubtitle = Color(0xFF655541),
    grayCommon = Color(0xFF92908e),
    backgroundNetworkDisconnected = Color(0x49e50a2a),
    backgroundSearchBar = Color(0xFFF3E9DF),
    iconTint = Color(0xFF000000),
    brush = Brush.verticalGradient(
        colors = listOf(
            Color(0x03FCF7F4),
            Color(0x4DF9F4EA),
            Color(0xF7F9F4EA)
        )
    ),
    navBarItemSelected = Color(0xFF7d4532),
    navBarItemUnselected = Color(0xFF4E5157),
    placeholderText = Color(0xFF92908e)
)

val DarkColors = CustomColors(
    backgroundSplash = Color(0xFF0C1015),
    textIconSplash = Color(0xFFF69C44),

    content = Color.White,
    backgroundDetails = Color(0xFF0C1015),
    backgroundHome = Color(0xFF0C1015),
    textColor = Color.White,

    coffeeCardBackgroundPrimary = Color(0xFF272B30),
    coffeeCardBackgroundSecondary = Color(0xFF272B30),
    coffeeCardBorder = Color(0xFF272B30),

    optionCategoryBorderEnabled = Color.White,
    optionCategoryBackgroundEnabled = Color(0xFFF69C44),
    optionCategoryTextEnabled = Color(0xFFFFFFFF),

    optionCategoryBorderDisabled = Color(0xFF0C1015),
    optionCategoryBackgroundDisabled = Color(0xFF0C1015),
    optionCategoryTextDisabled = Color(0xFFFFFFFF),

    headerHomeTitle = Color.White,
    headerHomeSubtitle = Color.White,
    grayCommon = Color.White,
    backgroundNetworkDisconnected = Color(0xFFFF4F6B),
    backgroundSearchBar = Color(0xFF272B30),
    iconTint = Color(0xFFFFFFFF),
    brush = Brush.verticalGradient(
        colors = listOf(
            Color(0x030C1015),
            Color(0x4D0C1015),
            Color(0xF70C1015)
        )
    ),
    navBarItemSelected = Color(0xFFF69C44),
    navBarItemUnselected = Color(0xFF666D75),
    placeholderText = Color(0xFF92908e)
)