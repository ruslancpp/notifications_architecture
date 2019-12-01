package com.infiniteset.notifications.modulemvvm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import com.infiniteset.notifications.notification.MvvmNotification

class SampleMvvmNotification(private val context: Context) : MvvmNotification() {

    override val channel = "Simple MVVM"

    private val viewModel = SampleViewModel()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onUpdateNotificationChannel() {
        val name = context.getString(R.string.mvvm_notification_name)
        val description = context.getString(R.string.mvvm_notification_description)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channel, name, importance)
        channel.description = description
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

    override fun onAttach(appNotificationManager: com.infiniteset.notifications.manager.NotificationManager) {
        super.onAttach(appNotificationManager)

        viewModel.counter.observe(this, Observer { counter ->
            onUpdateNotification(counter)
        })
    }

    override fun onDetach() {
        cancel(NOTIFICATION_ID)
        super.onDetach()
    }

    override fun onContentPressed(notificationId: Int) {
        super.onContentPressed(notificationId)
        viewModel.incrementCounter()
    }

    private fun onUpdateNotification(count: Int) {
        notify(NOTIFICATION_ID) {
            setSmallIcon(R.mipmap.ic_launcher)
            setContentTitle("MVVM notification click")
            setContentText(count.toString())
        }
    }

    private companion object {
        private const val NOTIFICATION_ID = 1
    }
}
