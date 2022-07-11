package com.danilp.aquariumhelper.presentation.screens.aquarium.edit

sealed class AquariumEditEvent {
    object DeleteButtonPressed: AquariumEditEvent()
    object InsertButtonPressed: AquariumEditEvent()
    data class NameChanged(val name: String) : AquariumEditEvent()
    data class LitersChanged(val liters: String) : AquariumEditEvent()
    data class DescriptionChanged(val description: String) : AquariumEditEvent()
}