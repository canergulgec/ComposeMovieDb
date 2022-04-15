package com.caner.composemoviedb.features.screen.search.state

sealed class TextEvent {
    data class OnValueChange(val text: String) : TextEvent()
    data class OnFocusChange(val isHintVisible: Boolean) : TextEvent()
}
