package com.alonso.ui_components.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ProvidableCompositionLocal
import androidx.compose.runtime.compositionLocalOf
import com.alonso.data.local.preferences.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val repository: UserPreferencesRepository
) : BaseViewModel() {


    private val _themeState = MutableStateFlow<Boolean>(false)
    val themeState: StateFlow<Boolean> = _themeState


    init {
        launchIO(
            task = {
                repository.themeConfig.collectLatest { value ->
                    _themeState.update { value }
                }
            }
        )
    }

    fun toggleTheme(value: Boolean) {
        launchIO(
            task = {
                _themeState.update { value }
                repository.toggleTheme(value)
            }
        )
    }
}

val LocalThemeViewModel: ProvidableCompositionLocal<ThemeViewModel> =
    compositionLocalOf {
        error(
            "No ThemeViewModel provided! " +
                    "Make sure to wrap all usages of app components in ThemeViewModel.",
        )
    }

object AppSharedViewModel {

    val themeViewModel: ThemeViewModel
        @Composable
        get() = LocalThemeViewModel.current


}