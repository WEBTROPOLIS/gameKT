package com.example.kotlingame.model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface DataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertData(task: Datos)

    @Insert
    suspend fun insertMultiData(list:List<Datos>)

    @Update
    suspend fun updateData(task: Datos)

    @Delete
    suspend fun deleteOneData(task: Datos)

    @Query("DELETE FROM DATA_TABLE")
    suspend fun deleteAll()

    @Query("SELECT * FROM DATA_TABLE")
    fun getAllData() : LiveData<List<Datos>>

    @Query("SELECT * FROM DATA_TABLE WHERE nameGame =:name")
    fun getDataNameGame(name:String) :LiveData<Datos>

    @Query ("SELECT * FROM DATA_TABLE WHERE id=:id")
    fun getDataId(id:Int):LiveData<Datos>

    @Query ("SELECT * FROM DATA_TABLE WHERE nameGame LIKE :textSearch || '%'")
    fun getSearch(textSearch: String):LiveData<Datos>

}