package com.danilp.aquariumhelper.domain.use_case.validation

data class ValidationResult(
    val successful: Boolean,
    val errorCode: Int? = null
)

