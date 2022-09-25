package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.alkalinity

sealed interface AlkalinityMeasure {
    object DKH : AlkalinityMeasure
    object Ppm : AlkalinityMeasure
    object MeqL : AlkalinityMeasure
    object MgL : AlkalinityMeasure
}