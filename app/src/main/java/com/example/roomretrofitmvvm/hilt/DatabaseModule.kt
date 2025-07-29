package com.example.roomretrofitmvvm.hilt

import android.content.Context
import androidx.room.Room
import com.example.roomretrofitmvvm.room.UserDao
import com.example.roomretrofitmvvm.hilt.UserDatabaseHilt
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideUserDao(@ApplicationContext context: Context): UserDao {
        val database = Room.databaseBuilder(
            context,
            UserDatabaseHilt::class.java,  // Your original DB class (no Hilt suffix needed)
            "UserDB"
        ).build()
        return database.getUserDao()  // Directly provide the DAO
    }
}