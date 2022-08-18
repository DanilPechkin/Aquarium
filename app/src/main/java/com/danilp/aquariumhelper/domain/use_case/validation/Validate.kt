package com.danilp.aquariumhelper.domain.use_case.validation

class Validate(
    private val blankFieldError: String?,
    private val decimalError: String?,
    private val integerError: String?,
    private val negativeValueError: String?
) {
    fun decimal(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = blankFieldError
            )
        else if (value.ifEmpty { "0" }.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                errorMessage = decimalError
            )
        else if (value.ifEmpty { "0" }.toDouble() < 0.0)
            ValidationResult(
                successful = false,
                errorMessage = negativeValueError
            )
        else
            ValidationResult(successful = true)

    fun integer(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = blankFieldError
            )
        else if (!value.ifEmpty { "0" }.all { it.isDigit() })
            ValidationResult(
                successful = false,
                errorMessage = integerError
            )
        else if (value.ifEmpty { "0" }.toInt() < 0)
            ValidationResult(
                successful = false,
                errorMessage = negativeValueError
            )
        else
            ValidationResult(successful = true)

    fun string(value: String): ValidationResult =
        if (value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = blankFieldError
            )
        else
            ValidationResult(successful = true)
}