package com.example.roomretrofitmvvm.hilt

import com.example.roomretrofitmvvm.api.UsersApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideUsersApiInterface(retrofit: Retrofit): UsersApiInterface {
        return retrofit.create(UsersApiInterface::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://fake-json-api.mock.beeceptor.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}