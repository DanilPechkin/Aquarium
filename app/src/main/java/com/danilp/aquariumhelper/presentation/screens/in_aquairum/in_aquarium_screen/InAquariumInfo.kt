package com.danilp.aquariumhelper.presentation.screens.in_aquairum.in_aquarium_screen

import javax.inject.Singleton

@Singleton
class InAquariumInfo {
    private var aquariumId: Int = 0

    fun setAquariumId(id: Int) {
        aquariumId = id
    }

    fun getAquariumId(): Int = aquariumId
}