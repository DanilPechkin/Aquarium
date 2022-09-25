package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

sealed interface AccountEvent {
    object SignOutButtonPressed : AccountEvent
    object DeleteAccountButtonPressed : AccountEvent
}
