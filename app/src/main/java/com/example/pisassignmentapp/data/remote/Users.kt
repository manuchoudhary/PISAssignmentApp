package com.example.pisassignmentapp.data.remote

import com.google.gson.annotations.SerializedName

data class Users (

    @SerializedName("name")
    var mName   : UserName,
    @SerializedName("picture")
    var mAvatar : UserPicture
)