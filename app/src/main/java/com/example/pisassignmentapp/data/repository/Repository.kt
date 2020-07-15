package com.example.pisassignmentapp.data.repository

import androidx.lifecycle.LiveData
import com.example.pisassignmentapp.data.local.UserEntity

interface Repository {

    fun getUsersFromDb() : LiveData<List<UserEntity>>

    fun getUsersFromApi()

    fun saveUsers(users : List<UserEntity>)

    fun deleteUsers()

    fun userAccepted(isAccepted: Boolean, id: Long)

    fun userRejected(isRejected: Boolean, id: Long)
}