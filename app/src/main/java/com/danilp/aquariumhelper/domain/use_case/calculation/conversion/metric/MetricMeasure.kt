package com.danilp.aquariumhelper.domain.use_case.calculation.conversion.metric

sealed interface MetricMeasure {
    object Centimeters : MetricMeasure
    object Millimeters : MetricMeasure
    object Feet : MetricMeasure
    object Inches : MetricMeasure
    object Meters : MetricMeasure
}