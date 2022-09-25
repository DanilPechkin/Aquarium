package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import com.danilp.aquariumhelper.domain.service.LogService
import com.danilp.aquariumhelper.domain.use_case.validation.Validate
import com.danilp.aquariumhelper.presentation.screens.ProfessionalAquaristViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val accountService: AccountService,
    private val validate: Validate,
    logService: LogService
) : ProfessionalAquaristViewModel(logService) {
    var state by mutableStateOf(SignInState())

    private val signEventChannel = Channel<SignEvent>()
    val signEvents = signEventChannel.receiveAsFlow()

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
        ).any { it.error != null }

        if (hasError) {
            state = state.copy(emailErrorCode = emailResult.error)
            state = state.copy(passwordErrorCode = passwordResult.error)
            return
        }

        viewModelScope.launch(logErrorExceptionHandler) {
            val oldUserId = accountService.getUserId()
            accountService.deleteAccount { error ->
                if (error != null) onError(error)
            }
            accountService.authenticate(state.email, state.password) { error ->
                if (error != null) {
                    onError(error)
                }
            }
            signEventChannel.send(SignEvent.Success)
        }
    }

    private fun sendRecoveryEmail() {
        val emailResult = validate.email(value = state.email, isRequired = true)

        if (emailResult.error != null) {
            state = state.copy(emailErrorCode = emailResult.error)
            return
        }

        viewModelScope.launch(logErrorExceptionHandler) {
            accountService.sendRecoveryEmail(state.email) { error ->
                if (error != null) onError(error)
            }
            signEventChannel.send(SignEvent.RecoveryEmailSend)
        }
    }

    sealed class SignEvent {
        object RecoveryEmailSend : SignEvent()
        object Success : SignEvent()
    }
}