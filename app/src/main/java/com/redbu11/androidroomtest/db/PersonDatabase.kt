package com.redbu11.androidroomtest.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * Database singleton
 */
@Database(entities = [Person::class],version = 1)
abstract class PersonDatabase : RoomDatabase() {

    abstract val personDAO : PersonDAO

    companion object{
        @Volatile
        private var INSTANCE : PersonDatabase? = null
        fun getInstance(context: Context):PersonDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        PersonDatabase::class.java,
                        "person_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}
