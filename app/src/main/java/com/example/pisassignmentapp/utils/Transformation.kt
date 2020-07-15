package com.example.pisassignmentapp.utils

import com.example.pisassignmentapp.data.local.UserEntity
import com.example.pisassignmentapp.data.remote.UserResponse

class Transformation {

    companion object {

        fun toUsersEtities(usersResponse: UserResponse): List<UserEntity> = usersResponse.getUserResponse(usersResponse)

    }
}
