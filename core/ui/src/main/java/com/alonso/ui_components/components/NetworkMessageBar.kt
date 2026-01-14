package com.alonso.ui_components.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R


@Composable
fun NetworkMessageBar(modifier: Modifier = Modifier, isConnected: Boolean) {
    AnimatedVisibility(
        visible = isConnected.not(),
        enter = fadeIn(animationSpec = tween()),
        exit = fadeOut(animationSpec = tween())
    ) {
        Row(
            modifier = modifier
                .background(color = CoffeeGoTheme.colors.backgroundNetworkDisconnected)
                .fillMaxWidth()
                .padding(vertical = 10.dp, horizontal = 20.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_network_disabled),
                contentDescription = ""
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "You have been disconnected. Please check your internet connection.",
                style = CoffeeGoTheme.typography.commonMediumTextStyle.copy(
                    fontWeight = FontWeight(
                        700
                    )
                )
            )
        }
    }
}



