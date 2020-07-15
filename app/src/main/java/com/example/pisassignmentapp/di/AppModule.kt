package com.example.pisassignmentapp.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.pisassignmentapp.BuildConfig
import com.example.pisassignmentapp.data.local.LocalDataSource
import com.example.pisassignmentapp.data.local.UserDao
import com.example.pisassignmentapp.data.remote.RemoteDataSource
import com.example.pisassignmentapp.data.remote.RemoteService
import com.example.pisassignmentapp.data.repository.RepositoryDataSource
import com.google.gson.GsonBuilder
import com.google.gson.stream.JsonReader
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    var mApplication : Application?= null

    constructor(application : Application) {

        mApplication = application
    }

    @Singleton
    @Provides
    fun providesRoomDataSource(): LocalDataSource {
        return Room.databaseBuilder(mApplication!!, LocalDataSource::class.java!!, "user_database")
            .build()
    }

    @Singleton
    @Provides
    fun providesUserDao(userRoomDatabase: LocalDataSource): UserDao {
        return userRoomDatabase.getUserDao()
    }

    @Provides
    fun providesAppContext(): Context {
        return mApplication!!
    }

    @Provides
    @Singleton
    fun providesRemoteService(): RemoteService {
        var gson = GsonBuilder().setLenient().create()
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
            .create(RemoteService::class.java!!)
    }

    @Provides
    @Singleton
    fun providesRemoteDataSource(remoteService: RemoteService): RemoteDataSource {
        return RemoteDataSource(remoteService)
    }

    @Provides
    @Singleton
    fun providesUserRepository(remoteDataSource: RemoteDataSource, roomDataSource: LocalDataSource): RepositoryDataSource {

        return RepositoryDataSource(remoteDataSource, roomDataSource)
    }
}