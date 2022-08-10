package com.danilp.aquariumhelper.domain.use_case.validation

import android.content.Context
import androidx.core.text.isDigitsOnly
import com.danilp.aquariumhelper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class Validate @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun decimal(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.this_field_cant_be_blank_validation_res)
            )
        else if (value.ifEmpty { "0" }.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.should_be_decimal_validation_res)
            )
        else
            ValidationResult(successful = true)

    fun integer(value: String, isRequired: Boolean = false): ValidationResult =
        if (isRequired && value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.this_field_cant_be_blank_validation_res)
            )
        else if (!value.ifEmpty { "0" }.isDigitsOnly())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.should_be_integer_validation_res)
            )
        else
            ValidationResult(successful = true)

    fun string(value: String): ValidationResult =
        if (value.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.this_field_cant_be_blank_validation_res)
            )
        else
            ValidationResult(successful = true)
}