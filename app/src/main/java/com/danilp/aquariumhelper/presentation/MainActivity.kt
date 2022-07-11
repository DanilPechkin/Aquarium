package com.danilp.aquariumhelper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danilp.aquariumhelper.presentation.screens.NavGraphs
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquariumHelperTheme {
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}