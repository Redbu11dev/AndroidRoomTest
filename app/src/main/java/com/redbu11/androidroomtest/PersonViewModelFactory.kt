package com.redbu11.androidroomtest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.redbu11.androidroomtest.db.PersonRepository
import java.lang.IllegalArgumentException

class PersonViewModelFactory(private val repository: PersonRepository):ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(PersonViewModel::class.java)){
            return PersonViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }

}