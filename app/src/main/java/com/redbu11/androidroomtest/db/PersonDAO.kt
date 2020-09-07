package com.redbu11.androidroomtest.db

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Data Access Object
 */
@Dao
interface PersonDAO {
    @Insert
    suspend fun insertPerson(person: Person) : Long

    @Update
    suspend fun updatePerson(person: Person) : Int

    @Delete
    suspend fun deletePerson(person: Person) : Int

    @Query("DELETE FROM person_data_table")
    suspend fun deleteAll() : Int

    @Query("SELECT * FROM person_data_table")
    fun getAllPersons():LiveData<List<Person>>
}
