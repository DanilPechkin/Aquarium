package com.danilp.aquariumhelper.domain.use_case

import android.content.Context
import androidx.core.text.isDigitsOnly
import com.danilp.aquariumhelper.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ValidateLiters @Inject constructor(
    @ApplicationContext private val context: Context
) {
    fun execute(liters: String): ValidationResult =
        if (liters.isBlank())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.this_field_cant_be_blank)
            )
        else if (!liters.isDigitsOnly())
            ValidationResult(
                successful = false,
                errorMessage = context.getString(R.string.should_be_numeric)
            )
        else
            ValidationResult(successful = true)
}