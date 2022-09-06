package com.danilp.aquariumhelper.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danilp.aquariumhelper.presentation.screens.aquarium.aquariums_screen.AquariumsScreen
import com.danilp.aquariumhelper.presentation.ui.theme.AquariumHelperTheme
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var analytics: FirebaseAnalytics
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        analytics = Firebase.analytics

        setContent {
            AquariumHelperTheme {
                AquariumsScreen()
            }
        }
    }
}