package com.danilp.aquariumhelper.domain.service

interface AccountService {
    fun hasUser(): Boolean
    fun isAnonymousUser(): Boolean
    fun getUserEmail(): String
    fun getUserId(): String
    fun authenticate(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun sendRecoveryEmail(email: String, onResult: (Throwable?) -> Unit)
    fun createAnonymousAccount(onResult: (Throwable?) -> Unit)
    fun linkAccount(email: String, password: String, onResult: (Throwable?) -> Unit)
    fun deleteAccount(onResult: (Throwable?) -> Unit)
    fun signOut()
}