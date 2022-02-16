package com.example.crudandroid.data.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.crudandroid.data.db.entity.InscricaoEntity

@Dao
interface InscricaoDAO {

    //Devido ao uso de currenttimes devemos anotar as funções como suspensa para levar até a camada de apresentação
    @Insert
    suspend fun insert(inscricao: InscricaoEntity): Long

    @Update
    suspend fun update(inscricao: InscricaoEntity)

    @Query("DELETE FROM inscricao WHERE id= :id")
     fun delete(id:Long)

    @Query("DELETE FROM inscricao")
     fun deleteAll()

    @Query("SELECT * FROM inscricao")
    fun getAll(): LiveData<List<InscricaoEntity>>


}