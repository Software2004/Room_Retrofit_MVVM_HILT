package com.example.roomretrofitmvvm.hilt

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.roomretrofitmvvm.models.User
import com.example.roomretrofitmvvm.room.UserDao

@Database(
    entities = [User::class],
    version = 1,
    exportSchema = false  // Optional: Disable schema export if not needed
)
abstract class UserDatabaseHilt : RoomDatabase() {
    abstract fun getUserDao(): UserDao

    // No need for companion object since Hilt manages the singleton
}