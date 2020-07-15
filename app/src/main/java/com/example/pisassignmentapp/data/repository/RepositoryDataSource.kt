package com.example.pisassignmentapp.data.repository

import android.util.Log
import androidx.annotation.NonNull
import androidx.lifecycle.LiveData
import com.example.pisassignmentapp.data.local.LocalDataSource
import com.example.pisassignmentapp.data.local.UserEntity
import com.example.pisassignmentapp.data.remote.RemoteDataSource
import com.example.pisassignmentapp.utils.Transformation
import rx.Completable
import rx.CompletableSubscriber
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class RepositoryDataSource constructor(private val remoteDataSource: RemoteDataSource,
                                       private val roomDataSource: LocalDataSource
) : Repository {

    private val TAG : String = RepositoryDataSource::class.java.simpleName

    /**
     * Get users from database
     */
    override fun getUsersFromDb(): LiveData<List<UserEntity>> = roomDataSource.getUserDao().getUsers()

    /**
     * Get users from api
     */
    override fun getUsersFromApi() {

        remoteDataSource.getUsersFromApi()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ result ->

                Completable.fromAction {
                    deleteUsers()
                    saveUsers(Transformation.toUsersEtities(result.body()!!))
                }
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : CompletableSubscriber {
                        override fun onSubscribe(@NonNull subscription : Subscription) {

                        }

                        override fun onCompleted() {
                            Log.i(TAG, "DataSource has been Populated")
                        }

                        override fun onError(@NonNull e: Throwable) {
                            e.printStackTrace()
                            Log.e(TAG, "DataSource hasn't been Populated")
                        }
                    })

            },
                { error -> Log.e(TAG, "{$error.message}") },
                { Log.d(TAG, "completed") })
    }

    /**
     * Save users into database
     */
    override fun saveUsers(users: List<UserEntity>) {

        roomDataSource.getUserDao().saveUsers(users)
    }



    /**
     * Delete all users
     */
    override fun deleteUsers() {

        roomDataSource.getUserDao().deleteUsers()
    }

    override fun userAccepted(isAccepted: Boolean, id: Long) {

        Completable.fromAction {
            roomDataSource.getUserDao().userAccepted(isAccepted, id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableSubscriber {
                override fun onSubscribe(@NonNull subscription : Subscription) {

                }

                override fun onCompleted() {
                    Log.i(TAG, "Accept updated")
                }

                override fun onError(@NonNull e: Throwable) {
                    e.printStackTrace()
                    Log.e(TAG, "Error while accept update : ${e.message}")
                }
            })
    }

    override fun userRejected(isRejected: Boolean, id: Long) {

        Completable.fromAction {
            roomDataSource.getUserDao().userRejected(isRejected, id)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CompletableSubscriber {
                override fun onSubscribe(@NonNull subscription : Subscription) {

                }

                override fun onCompleted() {
                    Log.i(TAG, "Reject Updated")
                }

                override fun onError(@NonNull e: Throwable) {
                    e.printStackTrace()
                    Log.e(TAG, "Error while reject update : ${e.message}")
                }
            })
    }
}