package com.danilp.aquariumhelper.domain.service.impl

import com.danilp.aquariumhelper.domain.service.StorageService
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(

) : StorageService {
    override fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit) {

    }

    override fun updateUserId(
        oldUserId: String,
        newUserId: String,
        onResult: (Throwable?) -> Unit
    ) {
        TODO("Not yet implemented")
    }

}