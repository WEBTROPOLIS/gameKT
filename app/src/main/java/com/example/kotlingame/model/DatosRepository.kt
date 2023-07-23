package com.example.kotlingame.model

import androidx.lifecycle.LiveData

class DatosRepository  (private val dataDao: DataDao) {

    val listAllTask: LiveData<List<Datos>> = dataDao.getAllData()

    suspend fun insertData(task: Datos){
        dataDao.insertData(task)
    }

    suspend fun updateData(task: Datos){
        dataDao.updateData(task)
    }

    suspend fun deleteAllData(){
        dataDao.deleteAll()
    }

    suspend fun deleteData(task: Datos){
        dataDao.deleteOneData(task)
    }

}