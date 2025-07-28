package com.example.roomretrofitmvvm

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.roomretrofitmvvm.databinding.ActivitySplashBinding
import com.example.roomretrofitmvvm.viewModel.SplashActivityViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    private val viewModel: SplashActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.apply {
            img.animate().alpha(1f).translationY(0F).setDuration(1000).start()
        }

        viewModel.startTimer(10000, 1000)

        viewModel.timeLeft.observe(this) { timeLeft ->
            binding.timerTextView.text = timeLeft
        }
        viewModel.timerFinished.observe(this) { timerFinished ->
            if (timerFinished) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }

}