package com.example.pisassignmentapp.data.remote

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RemoteService {
    @GET("api/")
    fun getUsersFromApi(@Query("results") count: Int) : Observable<Response<UserResponse>>
}