package com.danilp.aquariumhelper.presentation.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danilp.aquariumhelper.domain.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val accountService: AccountService
) : ViewModel() {
    fun checkAccount() {
        if (!accountService.hasUser()) createAnonymousAccount()
    }

    private fun createAnonymousAccount() {
        viewModelScope.launch {
            accountService.createAnonymousAccount {
                accountService.createAnonymousAccount { }
            }
        }
    }
}