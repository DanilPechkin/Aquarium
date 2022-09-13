package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_up

data class SignUpState(
    val email: String = "",
    val password: String = "",
    val repeatedPassword: String = "",
    val emailErrorCode: Int? = null,
    val passwordErrorCode: Int? = null,
    val repeatedPasswordErrorCode: Int? = null
)
