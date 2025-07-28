package com.example.roomretrofitmvvm.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val address: String,
    val company: String,
    val country: String,
    val email: String,
    val name: String,
    val phone: String,
    val photo: String,
    val state: String,
    val username: String,
    val zip: String
)