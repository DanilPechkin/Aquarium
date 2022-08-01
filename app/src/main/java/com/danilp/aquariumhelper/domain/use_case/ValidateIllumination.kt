package com.danilp.aquariumhelper.domain.use_case

import android.content.Context
import androidx.core.text.isDigitsOnly
import com.danilp.aquariumhelper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateIllumination @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun execute(illumination: String): ValidationResult =
        if (illumination.toDoubleOrNull() == null)
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.should_be_decimal_validation_res)
            )
        else
            ValidationResult(successful = true)
}