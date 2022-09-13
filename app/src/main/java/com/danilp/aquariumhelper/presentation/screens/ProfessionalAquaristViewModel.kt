package com.danilp.aquariumhelper.presentation.screens

import androidx.lifecycle.ViewModel
import com.danilp.aquariumhelper.domain.service.LogService
import kotlinx.coroutines.CoroutineExceptionHandler

open class ProfessionalAquaristViewModel(
    private val logService: LogService
) : ViewModel() {
    open val logErrorExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        logService.logNonFatalCrash(throwable)
    }

    open fun onError(error: Throwable) {
        logService.logNonFatalCrash(error)
    }
}