package com.example.crudandroid.data.db.entity;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "inscricao")

data class InscricaoEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val name:String,
    val email:String
)
