package com.danilp.aquariumhelper.presentation.screens.splash

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
class SplashViewModel @Inject constructor(
    private val accountService: AccountService,
    logService: LogService
) : ProfessionalAquaristViewModel(logService) {

    private val completeEventChannel = Channel<CompleteEvent>()
    val completeEvents = completeEventChannel.receiveAsFlow()

    fun checkAccount() {
        viewModelScope.launch {
            if (!accountService.hasUser()) {
                createAnonymousAccount()
            } else {
                completeEventChannel.send(CompleteEvent.Success)
            }
        }
    }

    private fun createAnonymousAccount() {
        viewModelScope.launch(logErrorExceptionHandler) {
            accountService.createAnonymousAccount { error ->
                if (error != null)
                    onError(error)
            }
            completeEventChannel.send(CompleteEvent.Success)
        }
    }

    sealed class CompleteEvent {
        object Success : CompleteEvent()
    }
}