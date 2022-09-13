package com.danilp.aquariumhelper.domain.use_case.validation

import android.util.Patterns

class Validate {
    fun email(value: String, isRequired: Boolean): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (!Patterns.EMAIL_ADDRESS.matcher(value).matches())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.EMAIL_PATTERN_ERROR
            )
        else
            ValidationResult(successful = true)

    fun password(value: String, isRequired: Boolean): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (value.length < 8)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.PASSWORD_IS_SHORT_ERROR
            )
        else if (!(value.any { it.isDigit() } && value.any { it.isLetter() }))
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.PASSWORD_PATTERN_ERROR
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
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (password != repeatedPassword)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.REPEAT_PASSWORD_ERROR
            )
        else
            ValidationResult(successful = true)

    fun decimal(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (value.ifEmpty { "0" }.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.DECIMAL_ERROR
            )
        else if (value.ifEmpty { "0" }.toDouble() < 0.0)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.NEGATIVE_VALUE_ERROR
            )
        else
            ValidationResult(successful = true)

    fun integer(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (value.ifEmpty { "0" }.toIntOrNull() == null)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.INTEGER_ERROR
            )
        else if (value.ifEmpty { "0" }.toInt() < 0)
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.NEGATIVE_VALUE_ERROR
            )
        else
            ValidationResult(successful = true)

    fun string(value: String): ValidationResult =
        if (value.isBlank())
            ValidationResult(
                successful = false,
                errorCode = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else
            ValidationResult(successful = true)
}