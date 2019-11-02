package com.infiniteset.notifications.manager

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.annotation.VisibleForTesting
import androidx.core.app.NotificationCompat
import androidx.core.content.getSystemService
import com.infiniteset.notifications.notification.BaseNotification
import com.infiniteset.notifications.notification.NotificationBuilder

/**
 * A basic implementation of [AppNotificationManager] that uses a broadcast receiver for handling
 * user's interaction with notification.
 */
class BasicAppNotificationManager<T : BroadcastReceiver>(
    private val context: Context,
    clazz: Class<T>
) : AppNotificationManager {
    companion object {
        private const val NOTIFICATION_ID = "NOTIFICATION_ID"
        private const val NOTIFICATION_TAG = "NOTIFICATION_TAG"
        val ACTION_CONTENT = BasicAppNotificationManager::class.java.name + "_content"
        val ACTION_DELETE = BasicAppNotificationManager::class.java.name + "_delete"
    }

    /**
     * A unique request code per [notify] call.
     * Request code should be unique per notification,
     * otherwise the last notified will disable action of previously notified notifications.
     */
    private var requestCounter = 1

    @VisibleForTesting
    val contentIntent = Intent(context, clazz)
        .setAction(ACTION_CONTENT)
    @VisibleForTesting
    val deleteIntent = Intent(context, clazz)
        .setAction(ACTION_DELETE)

    @Volatile
    private var started = false
    private val notifications = hashSetOf<BaseNotification>()

    private val notificationManager get() = context.getSystemService<NotificationManager>()

    @Synchronized
    override fun onSystemLocalChanged() {
        if (started) {
            notifications.forEach { it.onSystemLocalChanged() }
        }
    }

    @Synchronized
    override fun start() {
        if (started) return
        notifications.forEach { it.onAttach(this) }
        started = true
    }

    @Synchronized
    override fun stop() {
        if (!started) return
        started = false
        notifications.forEach { it.onDetach() }
        notificationManager?.cancelAll()
    }

    @Synchronized
    override fun registerNotification(notification: BaseNotification) {
        if (notifications.add(notification) && started && !notification.isAttached) {
            notification.onAttach(this)
        }
    }

    @Synchronized
    override fun deregisterNotification(notification: BaseNotification) {
        if (notifications.remove(notification) && notification.isAttached) {
            notification.onDetach()
        }
    }

    @Synchronized
    override fun onInterceptAction(context: Context, intent: Intent) {
        if (!started) return
        val intentTag = intent.tag
        val notification = notifications.firstOrNull { it.tag == intentTag } ?: return
        val notificationId = intent.notificationId
        when (intent.notificationAction) {
            Action.CONTENT -> notification.onContentPressed(notificationId)
            Action.DELETE -> notification.onDeleted(notificationId)
        }
    }

    override fun cancel(tag: String?, notificationId: Int) {
        notificationManager?.cancel(tag, notificationId)
    }

    override fun notify(
        tag: String?, notificationId: Int, channel: String,
        flag: Int,
        builder: NotificationBuilder
    ) {
        notify(tag, notificationId, channel) {
            setContentIntent(
                PendingIntent.getBroadcast(
                    context, requestCounter++,
                    createBroadcastIntent(tag, notificationId, contentIntent),
                    flag
                )
            )
            setDeleteIntent(
                PendingIntent.getBroadcast(
                    context, requestCounter,
                    createBroadcastIntent(tag, notificationId, deleteIntent),
                    flag
                )
            )
            builder()
        }
    }

    @VisibleForTesting
    fun createBroadcastIntent(tag: String?, notificationId: Int, baseIntent: Intent) =
        Intent(baseIntent)
            .addFlags(Intent.FLAG_RECEIVER_FOREGROUND)
            .putExtra(NOTIFICATION_ID, notificationId).apply {
                if (tag != null) {
                    putExtra(NOTIFICATION_TAG, tag)
                }
            }

    private fun notify(
        tag: String?,
        notificationId: Int,
        channel: String,
        builder: NotificationBuilder
    ) {
        val notification = with(NotificationCompat.Builder(context, channel)) {
            builder(this)
            build()
        }
        notificationManager?.notify(tag, notificationId, notification)
    }

    private val Intent.tag get() = getStringExtra(NOTIFICATION_TAG)
    private val Intent.notificationId get() = getIntExtra(NOTIFICATION_ID, -1)
    private val Intent.notificationAction
        get() = when (action) {
            ACTION_CONTENT -> Action.CONTENT
            ACTION_DELETE -> Action.DELETE
            else -> null
        }

    private enum class Action {
        CONTENT,
        DELETE
    }
}
