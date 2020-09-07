package com.redbu11.androidroomtest.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "person_data_table")
data class Person (

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "person_id")
    var id : Int,

    @ColumnInfo(name = "person_name")
    var name : String,

    @ColumnInfo(name = "person_email")
    var email : String

)
