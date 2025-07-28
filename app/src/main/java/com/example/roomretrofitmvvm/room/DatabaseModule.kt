package com.example.roomretrofitmvvm.room

import android.content.Context
import androidx.room.Room
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