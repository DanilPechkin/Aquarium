package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

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
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    private val validate: Validate
) : ViewModel() {
    var state by mutableStateOf(SignInState())

    fun onEvent(event: SignInEvent) {
        when (event) {
            is SignInEvent.EmailChanged -> {
                state = state.copy(email = event.email)
            }
            is SignInEvent.PasswordChanged -> {
                state = state.copy(password = event.password)
            }
            SignInEvent.SignInButtonPressed -> {
                signIn()
            }
            SignInEvent.ForgotPasswordButtonPressed -> {
                sendRecoveryEmail()
            }
        }
    }

    private fun signIn() {
        val emailResult = validate.email(value = state.email, isRequired = true)
        val passwordResult = validate.password(value = state.password, isRequired = true)

        val hasError = listOf(
            emailResult,
            passwordResult
        ).any { it.errorCode != null }

        if (hasError) {
            state = state.copy(emailErrorCode = emailResult.errorCode)
            state = state.copy(passwordErrorCode = passwordResult.errorCode)
            return
        }

        viewModelScope.launch {
            val oldUserId = accountService.getUserId()
            accountService.authenticate(state.email, state.password) { error ->
                if (error == null) {
                    accountService.linkAccount(state.email, state.password) {}
                }
            }
        }
    }

    private fun sendRecoveryEmail() {
        val emailResult = validate.email(value = state.email, isRequired = true)

        if (emailResult.errorCode != null) {
            state = state.copy(emailErrorCode = emailResult.errorCode)
            return
        }

        viewModelScope.launch {
            accountService.sendRecoveryEmail(state.email) {}
        }
    }
}