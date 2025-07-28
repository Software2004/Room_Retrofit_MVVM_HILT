package com.example.roomretrofitmvvm.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.roomretrofitmvvm.models.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsersList(users: List<User>)

    @Query("SELECT * FROM user")
    suspend fun getUsersList(): List<User>

    @Query("DELETE FROM user")
    suspend fun clearUsers()


}