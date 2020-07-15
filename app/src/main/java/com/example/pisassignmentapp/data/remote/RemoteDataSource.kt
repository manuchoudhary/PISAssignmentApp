package com.example.pisassignmentapp.data.remote

import retrofit2.Response
import rx.Observable

class RemoteDataSource constructor(private val remoteService: RemoteService) {

    fun getUsersFromApi() : Observable<Response<UserResponse>> = remoteService.getUsersFromApi(10)

}