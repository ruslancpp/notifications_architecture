package com.infiniteset.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class NotificationBroadcastReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val notificationManager = (context.applicationContext as App).notificationManager
        when (intent.action) {
            Intent.ACTION_LOCALE_CHANGED -> {
                notificationManager.onSystemLocalChanged()
            }
            else -> {
                notificationManager.onInterceptAction(context, intent)
            }
        }
    }
}
