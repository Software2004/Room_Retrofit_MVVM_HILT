package com.example.roomretrofitmvvm.application

import android.app.Application
import com.example.roomretrofitmvvm.api.RetrofitHelper
import com.example.roomretrofitmvvm.api.UsersApiInterface
import com.example.roomretrofitmvvm.repository.UserRepository
import com.example.roomretrofitmvvm.room.UserDatabase
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class UsersApplication : Application() {
    // Using by lazy so the database and the repository are only created when they're needed
    // rather than when the application starts
//    lateinit var userRepository: UserRepository

    override fun onCreate() {
        super.onCreate()
        initializer()
    }

    private fun initializer() {
        /*val usersApiInterface =
            RetrofitHelper.getRetrofitInstance().create(UsersApiInterface::class.java)
        val database = UserDatabase.getDatabaseInstance(applicationContext)
        userRepository = UserRepository(usersApiInterface, database, applicationContext)*/

    }
}