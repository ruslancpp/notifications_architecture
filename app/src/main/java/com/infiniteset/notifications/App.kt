package com.infiniteset.notifications

import android.app.Application
import com.infiniteset.notifications.manager.AppNotificationManager
import com.infiniteset.notifications.manager.BasicAppNotificationManager

class App : Application() {

    lateinit var notificationManager: AppNotificationManager
        private set

    override fun onCreate() {
        super.onCreate()
        notificationManager =
            BasicAppNotificationManager(this, NotificationBroadcastReceiver::class.java)
    }
}