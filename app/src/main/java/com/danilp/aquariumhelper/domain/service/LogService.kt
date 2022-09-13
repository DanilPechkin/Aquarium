package com.danilp.aquariumhelper.domain.service

interface LogService {
    fun logNonFatalCrash(throwable: Throwable)
}