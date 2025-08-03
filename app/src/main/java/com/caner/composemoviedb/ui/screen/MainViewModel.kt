package com.caner.composemoviedb.ui.screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caner.domain.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _bottomBarVisibility = mutableStateOf(true)
    val bottomBarVisibility: State<Boolean> = _bottomBarVisibility

    val isDarkTheme: Flow<Boolean> = userPreferencesRepository.isDarkTheme()

    fun setDarkModeEnabled(enable: Boolean) {
        viewModelScope.launch {
            userPreferencesRepository.setDarkTheme(enable)
        }
    }

    fun changeBottomBarVisibility(state: Boolean) {
        if (_bottomBarVisibility.value != state) {
            _bottomBarVisibility.value = state
        }
    }
}
