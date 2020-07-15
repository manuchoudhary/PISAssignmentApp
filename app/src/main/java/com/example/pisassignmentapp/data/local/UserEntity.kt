package com.example.pisassignmentapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(

    @PrimaryKey(autoGenerate = true)
    var id       : Long = 0,
    var name     : String,
    var avatar   : String,
    var accept   : Boolean,
    var rejected : Boolean
)