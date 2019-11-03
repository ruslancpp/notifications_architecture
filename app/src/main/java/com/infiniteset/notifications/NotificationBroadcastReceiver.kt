package com.infiniteset.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.infiniteset.notifications.manager.BasicAppNotificationManager

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = (context.applicationContext as App).notificationManager
        when (intent.action) {
            BasicAppNotificationManager.ACTION_CONTENT,
            BasicAppNotificationManager.ACTION_DELETE -> {
                notificationManager.onInterceptAction(context, intent)
            }
            "android.intent.action.LOCALE_CHANGED" -> {
                notificationManager.onSystemLocalChanged()
            }
        }
    }
}
