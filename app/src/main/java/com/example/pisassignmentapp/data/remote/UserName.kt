package com.example.pisassignmentapp.data.remote

import com.google.gson.annotations.SerializedName

data class UserName (

    @SerializedName("first")
    var mFirst : String,
    @SerializedName("last")
    var mLast : String
)
