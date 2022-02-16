package com.example.crudandroid.repository

import androidx.lifecycle.LiveData
import com.example.crudandroid.data.db.dao.InscricaoDAO
import com.example.crudandroid.data.db.entity.InscricaoEntity

class DatabaseDataSource(private val inscricaoDAO: InscricaoDAO) : InscricaoRepository{



    override suspend fun insertSubscriber(name: String, email: String): Long {

        val inscricao = InscricaoEntity(name = name, email = email)
        return inscricaoDAO.insert(inscricao)

    }

    override suspend fun updateSubscriber(id: Long, name: String, email: String) {

        val inscricao = InscricaoEntity(id= id, name = name, email = email)

        inscricaoDAO.update(inscricao)
    }

    override suspend fun deleteSubscriber(id: Long) {
        inscricaoDAO.delete(id)
    }

    override suspend fun deleteAllSubscribers() {
        inscricaoDAO.deleteAll()
    }

    override  fun getAllSubscribers(): LiveData<List<InscricaoEntity>> {
        return inscricaoDAO.getAll()
    }

}