package com.alonso.ui_components.components

import android.view.Gravity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.compose.ui.window.DialogWindowProvider
import com.alonso.designsystem.CoffeeGoTheme

@Composable
fun LoaderModal(isShow: Boolean) {
    if (isShow) {
        MyAppDialog {
            ContentLoader()
        }
    }
}

@Composable
fun ContentLoader(modifier: Modifier = Modifier) {
    Box(
        modifier
            .fillMaxSize()
            .background(CoffeeGoTheme.colors.backgroundHome),
        contentAlignment = Alignment.Center,
        content = {
            CircularProgressIndicator()
        }
    )
}


@Composable
fun MyAppDialog(
    dimAmountContent: Float = 0f,
    position: Int = Gravity.CENTER,
    dismissOnClickOutside: Boolean = false,
    onDismissRequest: () -> Unit = {},
    content: @Composable () -> Unit,
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            usePlatformDefaultWidth = false,
            decorFitsSystemWindows = false,
            dismissOnClickOutside = dismissOnClickOutside
        )
    ) {
        val dialogWindowProvider = LocalView.current.parent as? DialogWindowProvider
        dialogWindowProvider?.window?.let {
            it.setGravity(position)
            it.setDimAmount(dimAmountContent)
        }
        content()
    }
}
