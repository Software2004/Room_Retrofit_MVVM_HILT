package com.example.roomretrofitmvvm.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.roomretrofitmvvm.models.User
import com.example.roomretrofitmvvm.repository.UserRepository
import com.example.roomretrofitmvvm.responses.ResponseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {
    val showProgress = MutableLiveData(true)

    fun init() {
        viewModelScope.launch {
            userRepository.getUsersList()
            showProgress.postValue(false)
        }

    }

    val users: LiveData<ResponseResult<List<User>>>
        get() = userRepository.usersLiveData
}
