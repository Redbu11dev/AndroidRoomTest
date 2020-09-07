package com.redbu11.androidroomtest.db

/**
 * Database access Repository
 */
class PersonRepository(private val dao : PersonDAO) {

    val persons = dao.getAllPersons()

    suspend fun insert(person: Person):Long{
        return dao.insertPerson(person)
    }

    suspend fun update(person: Person):Int{
        return dao.updatePerson(person)
    }

    suspend fun delete(person: Person) : Int{
        return dao.deletePerson(person)
    }

    suspend fun deleteAll() : Int{
        return dao.deleteAll()
    }
}
