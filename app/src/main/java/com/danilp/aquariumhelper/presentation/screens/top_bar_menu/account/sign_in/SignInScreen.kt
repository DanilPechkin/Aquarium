package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.account.sign_in

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.AquariumTopBar
import com.danilp.aquariumhelper.presentation.screens.InfoFieldWithError
import com.danilp.aquariumhelper.presentation.screens.destinations.AccountScreenDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun SignInScreen(
    navigator: DestinationsNavigator
) {
    var isTopMenuExpanded by rememberSaveable { mutableStateOf(false) }
    var textFieldSize by remember { mutableStateOf(Size.Zero) }

    Scaffold(
        topBar = {
            AquariumTopBar(
                title = stringResource(R.string.signing_in_title),
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
            val focusManager = LocalFocusManager.current

            InfoFieldWithError(
                value = "",
                onValueChange = {},
                label = stringResource(R.string.email_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Next)
                    }
                ),
                maxLines = 1,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoFieldWithError(
                value = "",
                onValueChange = {},
                label = stringResource(R.string.password_label),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(
                            FocusDirection.Next
                        )
                    }
                ),
                maxLines = 1,
                singleLine = true,
                textFieldModifier = Modifier
                    .onGloballyPositioned { coordinates ->
                        textFieldSize = coordinates.size.toSize()
                    }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                modifier = Modifier.width(
                    with(LocalDensity.current) {
                        textFieldSize.width.toDp()
                    }
                ),
                horizontalArrangement = Arrangement.End
            ) {
                Button(onClick = { /*TODO*/ }) {
                    Text(
                        text = stringResource(
                            com.google.firebase.storage.R.string.common_signin_button_text
                        )
                    )
                }
            }
        }
    }
}