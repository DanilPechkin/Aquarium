package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.AquariumTopBar
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.danilp.aquariumhelper.presentation.screens.destinations.AccountScreenDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.SettingsScreenDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.SignInScreenDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.SignUpScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun AccountScreen(
    navigator: DestinationsNavigator,
    viewModel: AccountViewModel = hiltViewModel()
) {
    val state = viewModel.state

    var isTopMenuExpanded by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        topBar = {
            AquariumTopBar(
                title = stringResource(R.string.account_title),
                switchMenuVisibility = { isTopMenuExpanded = !isTopMenuExpanded },
                isMenuExpanded = isTopMenuExpanded,
                hideMenu = { isTopMenuExpanded = false },
                navigateBack = { navigator.navigateUp() },
                navigateToSettings = { navigator.navigate(SettingsScreenDestination) },
                navigateToAccount = { navigator.navigate(AccountScreenDestination()) }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            if (state.isAnonymous) {
                Button(
                    onClick = {
                        navigator.navigate(SignInScreenDestination())
                    }
                ) {
                    Text(stringResource(com.google.android.gms.base.R.string.common_signin_button_text))
                }
                OutlinedButton(
                    onClick = {
                        navigator.navigate(SignUpScreenDestination())
                    }
                ) {
                    Text(stringResource(R.string.sign_up_button))
                }
            } else {
                Text(text = state.email)
                Row {
                    Button(
                        onClick = {
                            viewModel.onEvent(AccountEvent.DeleteAccountButtonPressed)
                            navigator.clearBackStack(NavGraphs.root.startRoute)
                        }
                    ) {
                        Text("Delete account")
                    }
                    OutlinedButton(
                        onClick = {
                            viewModel.onEvent(AccountEvent.SignOutButtonPressed)
                            navigator.clearBackStack(NavGraphs.root.startRoute)
                        }
                    ) {
                        Text("Sign out")
                    }
                }
            }
        }
    }
}