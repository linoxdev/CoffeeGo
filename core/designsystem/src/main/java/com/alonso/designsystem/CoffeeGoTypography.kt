package com.alonso.designsystem

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Immutable
data class CustomTypography(
    val headlineMedium: TextStyle,
    val bodyMedium: TextStyle,
    val commonRegularTextStyle: TextStyle,
    val commonMediumTextStyle: TextStyle,
    val commonBoldTextStyle: TextStyle
)

val TypographyCoffee = CustomTypography(
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontSize = 24.sp,
        lineHeight = 32.sp,
        letterSpacing = 0.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.25.sp
    ),
    commonRegularTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        fontWeight = FontWeight.Normal
    ),

    commonMediumTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        color = Color.Black,
        fontWeight = FontWeight.Medium
    ),
    commonBoldTextStyle = TextStyle(
        fontSize = 12.sp,
        fontFamily = FontFamily(Font(R.font.roboto_flex)),
        color = Color.Black,
        fontWeight = FontWeight.Medium
    )
)