package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

sealed class SignInEvent {
    data class PasswordChanged(val password: String) : SignInEvent()
    data class EmailChanged(val email: String) : SignInEvent()
    object SignInButtonPressed : SignInEvent()
    object ForgotPasswordButtonPressed : SignInEvent()
}
