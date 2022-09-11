package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService
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
                viewModelScope.launch {
                    val oldUserId = accountService.getUserId()
                    accountService.createAccount(state.email, state.firstPassword) { error ->
                        if (error != null) {
                            accountService.linkAccount(state.email, state.firstPassword) {}
                        }
                    }
                }
            }
        }
    }
}