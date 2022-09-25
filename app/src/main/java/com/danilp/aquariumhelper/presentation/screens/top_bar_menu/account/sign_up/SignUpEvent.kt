package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_up

sealed interface SignUpEvent {
    data class EmailChanged(val email: String) : SignUpEvent
    data class FirstPasswordChanged(val password: String) : SignUpEvent
    data class SecondPasswordChanged(val password: String) : SignUpEvent
    object SignUpButtonPressed : SignUpEvent
}