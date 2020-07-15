package com.example.pisassignmentapp.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM users")
    fun getUsers() : LiveData<List<UserEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUsers(users : List<UserEntity>)

    @Query("DELETE FROM users")
    fun deleteUsers()

    @Query("UPDATE users SET accept = :isAccepted where id = :id")
    fun userAccepted(isAccepted: Boolean, id: Long)

    @Query("UPDATE users SET rejected = :isRejected where id = :id")
    fun userRejected(isRejected: Boolean, id: Long)
}