package com.example.roomretrofitmvvm.viewModel.vMFactories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.roomretrofitmvvm.repository.UserRepository
import com.example.roomretrofitmvvm.viewModel.MainActivityViewModel

class MAVMFactory(private val userRepository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainActivityViewModel(userRepository) as T
    }
}