package com.alonso.search.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alonso.designsystem.CoffeeGoTheme
import com.alonso.designsystem.R

@Composable
fun SearchBar(
    modifier: Modifier = Modifier,
    textChange: (String) -> Unit,
    textValue: String,
    autofocus: Boolean = false,
    onSearch: () -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = textValue,
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Text,
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions(
            onDone = { onSearch() }
        ),
        colors = TextFieldDefaults.colors(
            focusedTextColor = CoffeeGoTheme.colors.textColor,
            unfocusedTextColor = CoffeeGoTheme.colors.textColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            disabledTextColor = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f),
            disabledIndicatorColor = Color.Transparent,
            disabledLeadingIconColor = CoffeeGoTheme.colors.textColor,
            disabledPlaceholderColor = CoffeeGoTheme.colors.textColor,
            focusedPlaceholderColor = CoffeeGoTheme.colors.placeholderText,
            unfocusedPlaceholderColor = CoffeeGoTheme.colors.placeholderText
        ),

        onValueChange = { textChange(it) },
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = null,
                Modifier.size(22.dp),
                tint = CoffeeGoTheme.colors.iconTint
            )
        },
        placeholder = {
            Text(
                "What are you searching for?",
                style = TextStyle(fontSize = 14.sp, color = CoffeeGoTheme.colors.placeholderText)
            )
        },
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester)
            .background(color = CoffeeGoTheme.colors.backgroundSearchBar, RoundedCornerShape(12.dp))

    )

    LaunchedEffect(Unit) {
        if (autofocus) {
            focusRequester.requestFocus()
        }
    }
}
