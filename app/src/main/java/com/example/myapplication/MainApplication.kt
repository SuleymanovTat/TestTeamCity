package com.example.myapplication

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initOneSignal()
        initFirebaseRemoteConfig()
    }

    private fun initOneSignal() {
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.initWithContext(this)
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.setNotificationOpenedHandler { result: OSNotificationOpenedResult ->
            OneSignal.onesignalLog(
                OneSignal.LOG_LEVEL.VERBOSE,
                "OSNotificationOpenedResult result: $result"
            )
            Log.e("my", "setNotificationOpenedHandler")
        }

        OneSignal.setNotificationWillShowInForegroundHandler { notificationReceivedEvent: OSNotificationReceivedEvent ->
            notificationReceivedEvent.notification.additionalData.get("id")
            OneSignal.onesignalLog(
                OneSignal.LOG_LEVEL.VERBOSE, "NotificationWillShowInForegroundHandler fired!" +
                        " with notification event: " + notificationReceivedEvent.toString()
            )
            val notification = notificationReceivedEvent.notification
            val data = notification.additionalData
            notificationReceivedEvent.complete(null)
            Log.e("my", "setNotificationWillShowInForegroundHandler")
        }

        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.pauseInAppMessages(true)
        OneSignal.setLocationShared(false)
    }

    private fun initFirebaseRemoteConfig() {
        FirebaseApp.initializeApp(this)
        FirebaseRemoteConfig.getInstance().apply {
            //set this during development
            val configSettings = FirebaseRemoteConfigSettings.Builder()
                .setMinimumFetchIntervalInSeconds(0)
                .build()
            setConfigSettingsAsync(configSettings)
            //set this during development

            setDefaultsAsync(R.xml.remote_config_defaults)
            fetchAndActivate().addOnCompleteListener { task ->
                val updated = task.result
                if (task.isSuccessful) {
                    val updated = task.result
                    Log.d(TAG, "Config params updated: $updated")
                } else {
                    Log.d(TAG, "Config params updated: $updated")
                }
            }
        }

    }

    companion object {
        private const val ONESIGNAL_APP_ID = "b4baf5ae-d362-48ec-b8c7-604e672a9ce6"
        private const val TAG = "my"
    }
}