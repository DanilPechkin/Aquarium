package com.danilp.aquariumhelper.domain.service.impl

import com.danilp.aquariumhelper.domain.service.StorageService
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import javax.inject.Inject

class StorageServiceImpl @Inject constructor(

) : StorageService {
    override fun deleteAllForUser(userId: String, onResult: (Throwable?) -> Unit) {
        Firebase.firestore
            .collection(USERS_COLLECTION)
            .whereEqualTo(USER_ID, userId)
            .get()
            .addOnFailureListener { error -> onResult(error) }
            .addOnSuccessListener { result ->
                for (document in result) document.reference.delete()
                onResult(null)
            }
    }

    override fun updateUserId(
        oldUserId: String,
        newUserId: String,
        onResult: (Throwable?) -> Unit
    ) {
        Firebase.firestore
            .collection(USERS_COLLECTION)
            .whereEqualTo(USER_ID, oldUserId)
            .get()
            .addOnFailureListener { error -> onResult(error) }
            .addOnSuccessListener { result ->
                for (document in result) document.reference.update(USER_ID, newUserId)
                onResult(null)
            }
    }

    companion object {
        private const val USERS_COLLECTION = "users"
        private const val USER_ID = "id"
    }

}