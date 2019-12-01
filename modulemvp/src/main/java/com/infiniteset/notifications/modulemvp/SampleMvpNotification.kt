package com.infiniteset.notifications.modulemvp

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import com.infiniteset.notifications.notification.MvpNotification

class SampleMvpNotification(private val context: Context) :
    MvpNotification<SampleView, SampleMvpNotificationPresenter>(), SampleView {

    override val channel = "Simple MVP"

    override fun createPresenter() = SampleMvpNotificationPresenter()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdateNotificationChannel() {
        val name = context.getString(R.string.mvp_notification_name)
        val description = context.getString(R.string.mvp_notification_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channel, name, importance)
        channel.description = description
        val notificationManager =
            context.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onContentPressed(notificationId: Int) {
        super.onContentPressed(notificationId)
        presenter.processClick()
    }

    override fun onShowEvenText() {
        onUpdateNotification(R.string.even)
    }

    override fun onShowOddText() {
        onUpdateNotification(R.string.odd)
    }

    private fun onUpdateNotification(@StringRes content: Int) {
        notify(NOTIFICATION_ID) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("MVP notification click")
            setContentText(context.getString(content))
        }
    }

    override fun onClearNotifications() {
        cancel(NOTIFICATION_ID)
    }

    private companion object {
        private const val NOTIFICATION_ID = 1
    }
}