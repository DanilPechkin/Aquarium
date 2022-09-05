package com.danilp.aquariumhelper.presentation

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.danilp.aquariumhelper.R
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

        val sharedPreferences = getSharedPreferences(
            getString(R.string.in_aquarium_info_shared_preferences_key),
            Context.MODE_PRIVATE
        )

        with(sharedPreferences.edit()) {
            this.putString(
                getString(R.string.temperature_measure_id_key),
                getString(R.string.temp_measure_celsius)
            )
            this.putString(
                getString(R.string.capacity_measure_id_key),
                getString(R.string.capacity_measure_liters)
            )
            this.putString(
                getString(R.string.alkalinity_measure_id_key),
                getString(R.string.alkalinity_measure_dkh)
            )
            this.putString(
                getString(R.string.metric_measure_id_key),
                getString(R.string.metric_measure_meters)
            )
            commit()
        }

        setContent {
            AquariumHelperTheme {
                AquariumsScreen()
            }
        }
    }
}