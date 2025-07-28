package com.example.roomretrofitmvvm

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.roomretrofitmvvm.adapters.UserAdapter
import com.example.roomretrofitmvvm.application.UsersApplication
import com.example.roomretrofitmvvm.databinding.ActivityMainBinding
import com.example.roomretrofitmvvm.responses.ResponseResult
import com.example.roomretrofitmvvm.viewModel.MainActivityViewModel
import com.example.roomretrofitmvvm.viewModel.vMFactories.MAVMFactory
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val adapter: UserAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

/*
        val userRepository = (application as UsersApplication).userRepository

        viewModel =
            ViewModelProvider(this, MAVMFactory(userRepository))[MainActivityViewModel::class.java]
*/
        viewModel.init()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        viewModel.users.observe(this) {
            when (it) {
                is ResponseResult.Loading -> {
                    viewModel.showProgress.postValue(true)
                }

                is ResponseResult.Error -> {
                    Snackbar.make(binding.recyclerView, it.error.toString(), Snackbar.LENGTH_LONG)
                        .setAction("Retry") {
                        viewModel.init()
                        }.show()
                }

                is ResponseResult.Success -> {
                    if (it.data.isNullOrEmpty())
                        Snackbar.make(binding.recyclerView, "Something went wrong", Snackbar.LENGTH_LONG)
                            .setAction("Retry") {
                                viewModel.init()
                            }.show()
                    else
                    adapter.submitList(it.data)
                }
            }
        }
        binding.recyclerView.adapter = adapter

//        binding.recyclerView.addItemDecoration(DividerItemDecoration(this, VERTICAL))
    }

    fun insertQuote(view: View){

    }
}