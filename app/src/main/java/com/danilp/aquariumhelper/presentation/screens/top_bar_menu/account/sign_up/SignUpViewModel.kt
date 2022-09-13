package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_up

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import com.danilp.aquariumhelper.domain.use_case.validation.Validate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val accountService: AccountService,
    private val validate: Validate
) : ViewModel() {
    var state by mutableStateOf(SignUpState())

    fun onEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignUpEvent.FirstPasswordChanged -> {
                state = state.copy(password = event.password)
            }
            is SignUpEvent.SecondPasswordChanged -> {
                state = state.copy(repeatedPassword = event.password)
            }
            SignUpEvent.SignUpButtonPressed -> {
                signUp()
            }
        }
    }

    private fun signUp() {
        val emailResult = validate.email(value = state.email, isRequired = true)
        val passwordResult = validate.password(value = state.password, isRequired = true)
        val repeatedPasswordResult = validate.repeatPassword(
            password = state.password,
            repeatedPassword = state.repeatedPassword,
            isRequired = true
        )

        val hasError = listOf(
            emailResult,
            passwordResult,
            repeatedPasswordResult
        ).any { it.errorCode != null }

        if (hasError) {
            state = state.copy(emailErrorCode = emailResult.errorCode)
            state = state.copy(passwordErrorCode = passwordResult.errorCode)
            state = state.copy(repeatedPasswordErrorCode = repeatedPasswordResult.errorCode)
            return
        }

        viewModelScope.launch {
            val oldUserId = accountService.getUserId()
            accountService.createAccount(state.email, state.password) { error ->
                if (error != null) {
                    accountService.linkAccount(state.email, state.password) {}
                }
            }
        }
    }
}