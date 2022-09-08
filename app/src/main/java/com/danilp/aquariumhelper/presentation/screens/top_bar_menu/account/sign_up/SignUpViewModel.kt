package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(

) : ViewModel() {
    var state by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpEvent.FirstPasswordChanged -> {
                state = state.copy(firstPassword = event.password)
            }
            is SignUpEvent.SecondPasswordChanged -> {
                state = state.copy(secondPassword = event.password)
            }
            SignUpEvent.SignUpButtonPressed -> {
                TODO()
            }
        }
    }
}