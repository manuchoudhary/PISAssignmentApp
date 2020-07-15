package com.example.pisassignmentapp.ui.user

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.pisassignmentapp.App
import com.example.pisassignmentapp.data.local.UserEntity
import com.example.pisassignmentapp.data.repository.RepositoryDataSource
import javax.inject.Inject

class UserViewModel : ViewModel(), LifecycleObserver {

    private val TAG : String ?= UserViewModel::class.simpleName

    @Inject
    lateinit var mRepositoryDataSource : RepositoryDataSource

    init {

        App.mAppComponent.inject(this)
    }


    /**
     * Get users from database
     */
    fun getUsers() : LiveData<List<UserEntity>> {
        return mRepositoryDataSource.getUsersFromDb()
    }

    fun onAccept(id: Long) {
        mRepositoryDataSource.userAccepted(true, id)
    }

    fun onReject(id: Long) {
        mRepositoryDataSource.userRejected(true, id)
    }

}