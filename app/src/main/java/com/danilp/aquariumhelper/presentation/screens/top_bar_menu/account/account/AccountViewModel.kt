package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    var state by mutableStateOf(AccountState())

    private val completeEventChannel = Channel<CompleteEvent>()
    val completeEvents = completeEventChannel.receiveAsFlow()

    init {
        viewModelScope.launch {
            state = state.copy(isAnonymous = accountService.isAnonymousUser())
            if (!state.isAnonymous) {
                state = state.copy(email = accountService.getUserEmail())
            }
        }
    }

    fun onEvent(event: AccountEvent) {
        when (event) {
            AccountEvent.DeleteAccountButtonPressed -> {
                viewModelScope.launch {
                    accountService.deleteAccount { }
                    completeEventChannel.send(CompleteEvent.Success)
                }
            }
            AccountEvent.SignOutButtonPressed -> {
                viewModelScope.launch {
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