package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import com.danilp.aquariumhelper.domain.service.LogService
import com.danilp.aquariumhelper.presentation.screens.ProfessionalAquaristViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : ProfessionalAquaristViewModel(logService) {
    var state by mutableStateOf(AccountState())

    private val completeEventChannel = Channel<CompleteEvent>()
    val completeEvents = completeEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch(logErrorExceptionHandler) {
            state = state.copy(isAnonymous = accountService.isAnonymousUser())
            if (!state.isAnonymous) {
                state = state.copy(email = accountService.getUserEmail())
            }
        }
    }

    fun onEvent(event: AccountEvent) {
        when (event) {
            AccountEvent.DeleteAccountButtonPressed -> {
                viewModelScope.launch(logErrorExceptionHandler) {
                    accountService.deleteAccount { error ->
                        if (error != null) onError(error)
                    }
                    completeEventChannel.send(CompleteEvent.Success)
                }
            }
            AccountEvent.SignOutButtonPressed -> {
                viewModelScope.launch(logErrorExceptionHandler) {
                    accountService.signOut()
                    completeEventChannel.send(CompleteEvent.Success)
                }
            }
        }
    }

    sealed class CompleteEvent {
        object Success : CompleteEvent()
    }
}