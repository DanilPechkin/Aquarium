package com.danilp.aquariumhelper.presentation.screens.in_aquairum.calculator.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.danilp.aquariumhelper.R
import com.danilp.aquariumhelper.presentation.navigation.nav_graphs.InAquariumNavGraph
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalMaterial3Api::class)
@InAquariumNavGraph
@Destination
@Composable
fun CalculatorsList(
    navigator: DestinationsNavigator
) {
    Scaffold(
        topBar = {
            SmallTopAppBar(
                title = {
                    Text(text = stringResource(R.string.calculators_title))
                },
                navigationIcon = {
                    IconButton(onClick = { navigator.navigateUp() }) {
                        Icon(
                            imageVector = Icons.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.back_arrow_button)
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

    Text(text = "Calculators", modifier = Modifier.padding(paddingValues))
    }
}