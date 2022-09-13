package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.account

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.AquariumTopBar
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.danilp.aquariumhelper.presentation.screens.destinations.*
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

    val context = LocalContext.current

    LaunchedEffect(key1 = context) {
        viewModel.completeEvents.collect { event ->
            when (event) {
                is AccountViewModel.CompleteEvent.Success -> {
                    navigator.clearBackStack(NavGraphs.root.startRoute)
                    navigator.navigate(AquariumSplashScreenDestination())
                }
            }
        }
    }

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
                        }
                    ) {
                        Text(stringResource(R.string.delete_account_button))
                    }
                    OutlinedButton(
                        onClick = {
                            viewModel.onEvent(AccountEvent.SignOutButtonPressed)
                        }
                    ) {
                        Text(stringResource(R.string.sign_out_button))
                    }
                }
            }
        }
    }
}