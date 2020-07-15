package com.example.pisassignmentapp.di

import com.example.pisassignmentapp.App
import com.example.pisassignmentapp.ui.user.UserViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    fun inject (viewModel : UserViewModel)

    fun inject (dialApp : App)
}