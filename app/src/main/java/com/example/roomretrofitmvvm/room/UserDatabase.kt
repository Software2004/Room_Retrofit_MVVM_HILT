package com.example.roomretrofitmvvm.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomretrofitmvvm.models.User

@Database(entities = [User::class], version = 1)
abstract class UserDatabase() : RoomDatabase(){
    abstract fun getUserDao() : UserDao

    companion object{
        @Volatile
        private var instance : UserDatabase? = null
        fun getDatabaseInstance(context: Context) : UserDatabase{
            if (instance==null){
                synchronized(this){
                    instance = Room.databaseBuilder(context,UserDatabase::class.java,"UserDB").build()
                }
            }
            return instance!!
        }
    }

}