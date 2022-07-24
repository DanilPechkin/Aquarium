package com.danilp.aquariumhelper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danilp.aquariumhelper.presentation.screens.aquarium.aquariums_screen.AquariumsScreen
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AquariumHelperTheme {
                AquariumsScreen()
            }
        }
    }
}