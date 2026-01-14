package com.alonso.ui_components.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R


@Composable
fun AlertDialogError(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    MyAppDialog(dimAmountContent = 0.6f) {
        Column(
            modifier = modifier
                .width(300.dp)
                .background(
                    color = CoffeeGoTheme.colors.backgroundHome,
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(24.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ErrorIcon()
            ErrorTitle()
            ErrorMessage()
            RetryButton(onClick = onRetry)
        }
    }
}

@Composable
private fun ErrorIcon() {
    Image(
        painter = painterResource(id = R.drawable.ic_error),
        contentDescription = "Error icon",
        modifier = Modifier.size(70.dp)
    )
}

@Composable
private fun ErrorTitle() {
    Text(
        text = "Oops!",
        style = CoffeeGoTheme.typography.commonBoldTextStyle.copy(
            fontSize = 25.sp,
            color = CoffeeGoTheme.colors.textColor
        )
    )
}

@Composable
private fun ErrorMessage() {
    Text(
        text = "Something went wrong. Please try again later.",
        style = CoffeeGoTheme.typography.commonMediumTextStyle.copy(
            fontSize = 16.sp,
            color = CoffeeGoTheme.colors.textColor
        ),
        textAlign = TextAlign.Center
    )
}

@Composable
private fun RetryButton(onClick: () -> Unit) {
    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.Red
        )
    ) {
        Text(
            "Try Again",
            style = CoffeeGoTheme.typography.commonMediumTextStyle.copy(color = Color.White)
        )
    }
}