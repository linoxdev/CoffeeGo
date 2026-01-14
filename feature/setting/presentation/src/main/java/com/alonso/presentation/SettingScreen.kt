package com.alonso.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R
import com.alonso.ui_components.base.AppSharedViewModel
import com.alonso.ui_components.base.ThemeViewModel

@Composable
fun SettingScreen(
    modifier: Modifier = Modifier,
    themeViewModel: ThemeViewModel = AppSharedViewModel.themeViewModel
) {

    val isDarkTheme by themeViewModel.themeState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = modifier,
        containerColor = CoffeeGoTheme.colors.backgroundHome
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))
            AppVersionHeader()
            Spacer(modifier = Modifier.height(32.dp))
            ThemeSettingItem(
                isDarkTheme = isDarkTheme,
                onThemeToggle = { themeViewModel.toggleTheme(it) }
            )
        }
    }
}

@Composable
private fun AppVersionHeader(modifier: Modifier = Modifier) {
    val iconCoffee = if (CoffeeGoTheme.isDarkModeApp) R.drawable.ic_coffee_bean_dark
    else R.drawable.ic_coffee_bean
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .size(90.dp)
                .clip(CircleShape)
                .background(color = CoffeeGoTheme.colors.coffeeCardBackgroundSecondary)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(iconCoffee),
                modifier = Modifier.size(60.dp),
                contentDescription = null,
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Go Coffee \nv0.0.9",
            style = CoffeeGoTheme.typography.commonMediumTextStyle.copy(
                color = CoffeeGoTheme.colors.textColor,
                fontSize = 16.sp,
                textAlign = TextAlign.Center
            )
        )
    }
}


@Composable
private fun ThemeSettingItem(
    isDarkTheme: Boolean,
    onThemeToggle: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Icon(
                painter = painterResource(id = R.drawable.ic_dark_model),
                tint = CoffeeGoTheme.colors.textColor,
                contentDescription = null,
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                "Dark mode",
                style = CoffeeGoTheme.typography.commonMediumTextStyle.copy(
                    color = CoffeeGoTheme.colors.textColor,
                    textAlign = TextAlign.Center,
                    fontSize = 14.sp
                )
            )
        }

        Switch(
            enabled = !CoffeeGoTheme.isDarkModeSystem,
            checked = isDarkTheme,
            onCheckedChange = onThemeToggle,
            colors = SwitchDefaults.colors(
                checkedThumbColor = Color(0xFFF3E3CA),
                checkedTrackColor = Color(0xFFA67415),
                uncheckedThumbColor = Color(0xFF673E22),
                uncheckedTrackColor = Color(0xFFE6D3C7),
                disabledCheckedBorderColor = Color.Transparent,
                uncheckedBorderColor = Color.Transparent
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingScreenPreview() {
    SettingScreen()
}
