package com.example.kotlingame.viewmodel

import android.app.Application

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.kotlingame.model.Datos
import com.example.kotlingame.model.DatosDatabase
import com.example.kotlingame.model.DatosRepository

import kotlinx.coroutines.launch



class DataViewModel(application: Application): AndroidViewModel(application) {
    private val repository: DatosRepository

    val allTask : LiveData<List<Datos>>

    init {
        val DataDao= DatosDatabase.getDatabase(application).getDataDao()
        repository = DatosRepository(DataDao)
        allTask = repository.listAllTask
    }

    fun insertData(task: Datos)=viewModelScope.launch{
        repository.insertData(task)
    }

    fun updateData(task: Datos)=viewModelScope.launch {
        repository.updateData(task)
    }

    fun deleteData(task: Datos)= viewModelScope.launch {

        repository.deleteData(task)
    }

    private val selectedData : MutableLiveData<Datos?> = MutableLiveData()

    fun selectedItem():LiveData<Datos?> = selectedData

    fun selected(task : Datos){
        selectedData.value = task
    }

}