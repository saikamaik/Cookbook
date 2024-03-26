package com.example.cookbook.presentation.view.homeScreen.uiEvent

sealed class HomeUiEvent {

    data class ChangeSelectedTabOption(val item: String): HomeUiEvent()

}