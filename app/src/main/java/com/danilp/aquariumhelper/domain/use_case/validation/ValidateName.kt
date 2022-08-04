package com.danilp.aquariumhelper.domain.use_case.validation

import android.content.Context
import com.danilp.aquariumhelper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateName @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun execute(name: String): ValidationResult =
        if (name.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.this_field_cant_be_blank_validation_res)
            )
        else
            ValidationResult(successful = true)
}