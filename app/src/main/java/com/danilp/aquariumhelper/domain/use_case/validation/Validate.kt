package com.danilp.aquariumhelper.domain.use_case.validation

import android.util.Patterns

class Validate {
    fun email(value: String, isRequired: Boolean): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches())
            ValidationResult(
                successful = false,
                error = ValidationError.EmailPatternError
            )
        else
            ValidationResult(successful = true)

    fun password(value: String, isRequired: Boolean): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else if (value.length < 8)
            ValidationResult(
                successful = false,
                error = ValidationError.PasswordShortError
            )
        else if (!(value.any { it.isDigit() } && value.any { it.isLetter() }))
            ValidationResult(
                successful = false,
                error = ValidationError.PasswordPatternError
            )
        else
            ValidationResult(successful = true)

    fun repeatPassword(
        password: String,
        repeatedPassword: String,
        isRequired: Boolean
    ): ValidationResult =
        if (isRequired && repeatedPassword.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else if (password != repeatedPassword)
            ValidationResult(
                successful = false,
                error = ValidationError.RepeatPasswordError
            )
        else
            ValidationResult(successful = true)

    fun decimal(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else if (value.ifEmpty { "0" }.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                error = ValidationError.DecimalError
            )
        else if (value.ifEmpty { "0" }.toDouble() < 0.0)
            ValidationResult(
                successful = false,
                error = ValidationError.NegativeValueError
            )
        else
            ValidationResult(successful = true)

    fun integer(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else if (value.ifEmpty { "0" }.toIntOrNull() == null)
            ValidationResult(
                successful = false,
                error = ValidationError.IntegerError
            )
        else if (value.ifEmpty { "0" }.toInt() < 0)
            ValidationResult(
                successful = false,
                error = ValidationError.NegativeValueError
            )
        else
            ValidationResult(successful = true)

    fun string(value: String): ValidationResult =
        if (value.isBlank())
            ValidationResult(
                successful = false,
                error = ValidationError.BlankFieldError
            )
        else
            ValidationResult(successful = true)
}