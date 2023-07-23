package com.example.kotlingame.model

import android.content.Context
import android.provider.CalendarContract.Instances
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Datos:: class], version =1)
abstract class DatosDatabase : RoomDatabase(){
    abstract fun getDataDao(): DataDao

    companion object{
        @Volatile
        private var INSTANCE: DatosDatabase? = null

        fun getDatabase (context: Context): DatosDatabase{
            val tempInstance = INSTANCE
            if(tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DatosDatabase::class.java,
                    "DataEntity"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}