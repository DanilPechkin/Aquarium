package com.danilp.aquariumhelper.presentation.screens.top_bar_menu.settings

data class SettingsState(
    val capacityMeasure: String = "",
    val temperatureMeasure: String = "",
    val alkalinityMeasure: String = "",
    val metricMeasure: String = "",
    val capacityList: List<String> = listOf(),
    val temperatureList: List<String> = listOf(),
    val alkalinityList: List<String> = listOf(),
    val metricList: List<String> = listOf()
)