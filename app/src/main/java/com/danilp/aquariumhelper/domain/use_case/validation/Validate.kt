package com.danilp.aquariumhelper.domain.use_case.validation

class Validate {
    fun decimal(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (value.ifEmpty { "0" }.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.DECIMAL_ERROR
            )
        else if (value.ifEmpty { "0" }.toDouble() < 0.0)
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.NEGATIVE_VALUE_ERROR
            )
        else
            ValidationResult(successful = true)

    fun integer(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else if (value.ifEmpty { "0" }.toIntOrNull() == null)
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.INTEGER_ERROR
            )
        else if (value.ifEmpty { "0" }.toInt() < 0)
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.NEGATIVE_VALUE_ERROR
            )
        else
            ValidationResult(successful = true)

    fun string(value: String): ValidationResult =
        if (value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = ValidationErrorCode.BLANK_FIELD_ERROR
            )
        else
            ValidationResult(successful = true)
}