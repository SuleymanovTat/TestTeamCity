package com.example.myapplication

import android.app.Application
import com.onesignal.OSNotificationOpenedResult
import com.onesignal.OSNotificationReceivedEvent
import com.onesignal.OneSignal


class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Enable verbose OneSignal logging to debug issues if needed.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.VERBOSE, OneSignal.LOG_LEVEL.NONE)
        // OneSignal Initialization
        OneSignal.setAppId(ONESIGNAL_APP_ID)
        OneSignal.initWithContext(this)
        OneSignal.setNotificationOpenedHandler { result: OSNotificationOpenedResult ->
            OneSignal.onesignalLog(
                OneSignal.LOG_LEVEL.VERBOSE,
                "OSNotificationOpenedResult result: $result"
            )
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
        }

        OneSignal.unsubscribeWhenNotificationsAreDisabled(true)
        OneSignal.pauseInAppMessages(true)
        OneSignal.setLocationShared(false)
    }

    companion object {
        private const val ONESIGNAL_APP_ID = "b4baf5ae-d362-48ec-b8c7-604e672a9ce6"
    }
}