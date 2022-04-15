package com.caner.composemoviedb.features.screen.main

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.composemoviedb.data.local.ThemeManager
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val themeManager: ThemeManager
) : ViewModel() {

    private val _bottomBarVisibility = mutableStateOf(true)
    val bottomBarVisibility: State<Boolean> = _bottomBarVisibility

    fun setDarkModeEnabled(enable: Boolean) {
        viewModelScope.launch {
            themeManager.setDarkMode(enable)
        }
    }

    fun changeBottomBarVisibility(state: Boolean) {
        if (_bottomBarVisibility.value != state) {
            _bottomBarVisibility.value = state
        }
    }
}
