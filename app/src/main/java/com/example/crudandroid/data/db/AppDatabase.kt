package com.example.crudandroid.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.crudandroid.data.db.dao.InscricaoDAO
import com.example.crudandroid.data.db.entity.InscricaoEntity

@Database(entities = [InscricaoEntity::class], version = 1 )

//Como a classe vai ser compilada em tempo de execução se torna necessario a amarcação abstract
abstract class AppDatabase : RoomDatabase() {

    abstract val inscricaoDAO: InscricaoDAO

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
//permite apenas umas instancia do banco de dados
        fun getInstance(context: Context): AppDatabase {
            synchronized(this) {
                var instance: AppDatabase? = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        context,
                        AppDatabase::class.java,
                        "app_database"
                    ).build()
                }

                return instance
            }
        }
    }

}