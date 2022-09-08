package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(

) : ViewModel() {
    var state by mutableStateOf(SignInState())

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanched -> {
                state = state.copy(email = event.email)
            }
            is SignInEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            SignInEvent.SignInButtonPresed -> {
                TODO()
            }
        }
    }
}