package com.example.pisassignmentapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import javax.inject.Singleton

@Singleton
@Database(entities = arrayOf(UserEntity::class), version = 1)
abstract class LocalDataSource : RoomDatabase() {

    abstract fun getUserDao() : UserDao

}
