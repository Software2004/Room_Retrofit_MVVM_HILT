package com.example.roomretrofitmvvm.repository

import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.roomretrofitmvvm.api.UsersApiInterface
import com.example.roomretrofitmvvm.models.User
import com.example.roomretrofitmvvm.responses.ResponseResult
import com.example.roomretrofitmvvm.room.UserDao
import com.example.roomretrofitmvvm.room.UserDatabase
import com.example.roomretrofitmvvm.utils.NetworkUtil
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class UserRepository @Inject constructor(
    private val usersApiInterface: UsersApiInterface,
    private val userDao: UserDao,
    @ApplicationContext private val context: Context
) {

    private val usersMutableLiveData = MutableLiveData<ResponseResult<List<User>>>()

    val usersLiveData: LiveData<ResponseResult<List<User>>>
        get() = usersMutableLiveData

    suspend fun getUsersList() {

        if (NetworkUtil.isInternetAvailable(context)) {
            try {
                val result = usersApiInterface.getUsersFromAPI()
                if (result.body() != null) {
                    Log.e(
                        TAG,
                        "getUsersList: Data obtained from API -> Size : " + result.body()!!.size
                    )
                    userDao.clearUsers()
                    userDao.insertUsersList(result.body()!!)
                    usersMutableLiveData.postValue(ResponseResult.Success(result.body()))
                } else {
                    //usersMutableLiveData.postValue(ResponseResult.Error("Something went wrong in API"))
                    checkFromRoom()
                }
            } catch (e: Exception) {
                //usersMutableLiveData.postValue(ResponseResult.Error(e.message.toString()))
                checkFromRoom()
                Log.e(TAG, "getUsersList: Exception -> " + e.message.toString())
            }
        } else {
            checkFromRoom()
        }


    }

    private suspend fun checkFromRoom() {
        try {
            usersMutableLiveData.postValue(
                ResponseResult.Success(
                    userDao.getUsersList()
                )
            )
        } catch (e: Exception) {
            usersMutableLiveData.postValue(ResponseResult.Error(e.message.toString()))
        }
    }

}

