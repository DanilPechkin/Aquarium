package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.screens.InfoFieldWithError
import com.danilp.aquariumhelper.presentation.screens.destinations.AquariumListDestination
import com.danilp.aquariumhelper.presentation.screens.destinations.InAquariumScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@RootNavGraph
@Destination
@Composable
fun AquariumEdit(
    aquariumId: Int,
    navigator: DestinationsNavigator,
    viewModel: AquariumEditViewModel = hiltViewModel()
) {
    val state = viewModel.state

    LaunchedEffect(key1 = LocalContext.current) {
        viewModel.validationEvents.collect { event ->
            when (event) {
                is AquariumEditViewModel.ValidationEvent.Success -> {
                    navigator.navigate(InAquariumScreenDestination)
                }
            }
        }
    }

    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.add_aquarium_title),
                        modifier = Modifier.padding(horizontal = 4.dp),
                        maxLines = 1
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = { navigator.navigate(AquariumListDestination) }
                    ) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = stringResource(R.string.content_back_arrow)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->
        Column(
            Modifier
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            InfoFieldWithError(
                value = state.name,
                onValueChange = { viewModel.onEvent(AquariumEditEvent.NameChanged(it)) },
                label = stringResource(R.string.name_label),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
                errorMessage = state.nameError,
                maxLines = 1,
                singleLine = true
            )

            Spacer(modifier = Modifier.height(16.dp))

            InfoFieldWithError(
                value = state.liters,
                onValueChange = { viewModel.onEvent(AquariumEditEvent.LitersChanged(it)) },
                label = stringResource(R.string.liters),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                errorMessage = state.litersError,
                maxLines = 1,
                singleLine = true
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = state.description,
                onValueChange = { viewModel.onEvent(AquariumEditEvent.DescriptionChanged(it)) },
                label = {
                    Text(text = stringResource(R.string.description_label))
                }
            )
            Row(
                Modifier.padding(16.dp)
            ) {
                Button(
                    onClick = {
                        viewModel.onEvent(AquariumEditEvent.DeleteButtonPressed)
                    }
                ) {
                    Text(text = stringResource(R.string.delete_button))
                }
                Spacer(modifier = Modifier.width(16.dp))
                Button(
                    onClick = {
                        viewModel.onEvent(AquariumEditEvent.InsertButtonPressed)
                    }
                ) {
                    Text(text = stringResource(R.string.save_aquarium_button))
                }
            }
        }
    }
}