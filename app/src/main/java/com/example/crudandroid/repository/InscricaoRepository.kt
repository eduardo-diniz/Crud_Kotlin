package com.example.crudandroid.repository

import androidx.lifecycle.LiveData
import com.example.crudandroid.data.db.entity.InscricaoEntity

interface InscricaoRepository {

    suspend fun insertSubscriber(name: String, email: String): Long

    suspend fun updateSubscriber(id: Long, name: String, email: String)

    suspend fun deleteSubscriber(id: Long)

    suspend fun deleteAllSubscribers()

    fun getAllSubscribers(): LiveData<List<InscricaoEntity>>

}