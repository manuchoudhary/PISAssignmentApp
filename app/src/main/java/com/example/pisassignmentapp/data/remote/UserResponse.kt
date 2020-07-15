package com.example.pisassignmentapp.data.remote

import com.example.pisassignmentapp.data.local.UserEntity
import com.google.gson.annotations.SerializedName

data class UserResponse (

    @SerializedName("results")
    var userList   : List<Users>
){
    fun getUserResponse(userResponse: UserResponse) : List<UserEntity> {
        var userList = userResponse.userList
        val userEntityList = mutableListOf<UserEntity>()
        userList.forEach{
            userEntityList += UserEntity(0,
                it.mName.mFirst + " " + it.mName.mLast,
                it.mAvatar.mProfilePic,
                accept = false,
                rejected = false
            )
        }
        return userEntityList
    }
}