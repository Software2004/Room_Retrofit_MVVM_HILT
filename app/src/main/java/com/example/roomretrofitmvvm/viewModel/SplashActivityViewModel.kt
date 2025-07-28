package com.example.roomretrofitmvvm.viewModel

import android.os.CountDownTimer
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.util.Locale

class SplashActivityViewModel : ViewModel() {

    private val _timeLeft = MutableLiveData<String>()
    val timeLeft: LiveData<String> get() = _timeLeft


    private val _timerFinished = MutableLiveData<Boolean>()
    val timerFinished: LiveData<Boolean> get() = _timerFinished


    private var countDownTimer: CountDownTimer? = null


    fun startTimer(duration: Long, interval: Long) {
        countDownTimer = object : CountDownTimer(duration, interval) {
            override fun onTick(millisUntilFinished: Long) {
                val minutes = (millisUntilFinished / 1000) / 60
                val seconds = (millisUntilFinished / 1000) % 60
                val timeLeftFormatted =
                    String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
                _timeLeft.value = "Time left: $timeLeftFormatted"
            }

            override fun onFinish() {
                _timeLeft.value = "Time's finished!"
                _timerFinished.value = true
            }
        }.start()
    }

    override fun onCleared() {
        super.onCleared()
        countDownTimer?.cancel()
    }

}