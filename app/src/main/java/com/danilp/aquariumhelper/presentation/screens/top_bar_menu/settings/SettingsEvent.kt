package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.settings

sealed class SettingsEvent {
    object SaveButtonPressed : SettingsEvent()
    object DefaultButtonPressed : SettingsEvent()
    data class CapacityChanged(val capacity: String) : SettingsEvent()
    data class MetricChanged(val metric: String) : SettingsEvent()
    data class AlkalinityChanged(val alkalinity: String) : SettingsEvent()
    data class TemperatureChanged(val temperature: String) : SettingsEvent()
}