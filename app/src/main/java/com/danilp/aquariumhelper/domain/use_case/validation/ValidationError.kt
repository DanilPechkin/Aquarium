package com.danilp.aquariumhelper.domain.use_case.validation

sealed interface ValidationError {
    object BlankFieldError : ValidationError
    object DecimalError : ValidationError
    object IntegerError : ValidationError
    object NegativeValueError : ValidationError
    object EmailPatternError : ValidationError
    object PasswordShortError : ValidationError
    object PasswordPatternError : ValidationError
    object RepeatPasswordError : ValidationError
}
