package com.example.pisassignmentapp

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex
import com.example.pisassignmentapp.data.repository.RepositoryDataSource
import com.example.pisassignmentapp.di.AppComponent
import com.example.pisassignmentapp.di.AppModule
import com.example.pisassignmentapp.di.DaggerAppComponent
import javax.inject.Inject

class App : Application() {

    @Inject
    lateinit var mRepositoryDataSource : RepositoryDataSource

    companion object {

        lateinit var mAppComponent: AppComponent
    }

    init {

        initializeDagger()
    }

    override fun onCreate() {
        super.onCreate()

        mAppComponent.inject(this)

        //Get users from api
        mRepositoryDataSource.getUsersFromApi()
    }

    fun initializeDagger (){

        mAppComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}