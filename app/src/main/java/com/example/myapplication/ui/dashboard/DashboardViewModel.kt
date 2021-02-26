package com.example.myapplication.ui.dashboard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.remoteconfig.FirebaseRemoteConfig

class DashboardViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        val remoteConfig = FirebaseRemoteConfig.getInstance()
        val isAuth = remoteConfig.getString("isAuth")
        val message = remoteConfig.getString("title")
        value = "isAuth: $isAuth\n$message"
    }
    val text: LiveData<String> = _text
}