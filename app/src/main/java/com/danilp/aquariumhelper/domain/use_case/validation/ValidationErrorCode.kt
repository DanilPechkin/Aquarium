package com.danilp.aquariumhelper.domain.use_case.validation

object ValidationErrorCode {
    const val BLANK_FIELD_ERROR = 1
    const val DECIMAL_ERROR = 2
    const val INTEGER_ERROR = 3
    const val NEGATIVE_VALUE_ERROR = 4
    const val EMAIL_PATTERN_ERROR = 5
    const val PASSWORD_IS_SHORT_ERROR = 6
    const val PASSWORD_PATTERN_ERROR = 7
    const val REPEAT_PASSWORD_ERROR = 8
}
