package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

data class SignInState(
    val email: String = "",
    val password: String = "",
    val emailErrorCode: Int? = null,
    val passwordErrorCode: Int? = null
)
