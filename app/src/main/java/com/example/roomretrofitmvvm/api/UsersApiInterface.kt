package com.example.roomretrofitmvvm.api

import com.example.roomretrofitmvvm.models.User
import retrofit2.Response
import retrofit2.http.GET

interface UsersApiInterface  {

    @GET("/users")
    suspend fun getUsersFromAPI() : Response<List<User>>

//    https://fake-json-api.mock.beeceptor.com/users
//    inCase if the link has some query parameters use the below declaration
//    suspend fun getUsersList(@Query("page") page : Int) : Response<User>

}