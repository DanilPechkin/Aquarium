package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

sealed class SignInEvent {
    data class PasswordChanged(val password: String) : SignInEvent()
    data class EmailChanched(val email: String) : SignInEvent()
    object SignInButtonPresed : SignInEvent()
}
