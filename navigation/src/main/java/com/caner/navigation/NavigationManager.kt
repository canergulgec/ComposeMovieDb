package com.caner.navigation

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NavigationManager {
    private val _commands = MutableStateFlow<NavigationCommand?>(null)
    val commands = _commands.asStateFlow()

    fun navigate(route: String) {
        _commands.value = NavigationCommand(route)
    }
}