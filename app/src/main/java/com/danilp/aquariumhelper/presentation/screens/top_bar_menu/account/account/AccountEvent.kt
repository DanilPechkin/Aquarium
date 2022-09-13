package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

sealed class AccountEvent {
    object SignOutButtonPressed : AccountEvent()
    object DeleteAccountButtonPressed : AccountEvent()
}
