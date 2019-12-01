package com.infiniteset.notifications.notification

import android.app.PendingIntent
import android.os.Build
import androidx.annotation.CallSuper
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.infiniteset.notifications.manager.NotificationManager

typealias NotificationBuilder = NotificationCompat.Builder.() -> Unit

/**
 * Base class for local notifications. This class handles basic operation with local notifications.
 * Handles a setup for receiving content pressed and delete notification events. Interception of aforementioned events
 * is based on registering/unregistering an implicit broadcast receiver when notification is shown/dismissed.
 * To intercept these event simply override [onContentPressed] and [onDeleted] methods.
 */
abstract class BaseNotification(protected val isForeground: Boolean = false) {

    val tag get() = javaClass.name.takeIf { !isForeground }
    protected abstract val channel: String

    @Volatile
    protected var notificationManager: NotificationManager? = null
    @Volatile
    var isAttached = false
        private set

    @RequiresApi(Build.VERSION_CODES.O)
    protected abstract fun onUpdateNotificationChannel()

    @CallSuper
    open fun onSystemLocalChanged() {
        if (isAttached && Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            onUpdateNotificationChannel()
        }
    }

    @CallSuper
    open fun onAttach(appNotificationManager: NotificationManager) {
        this.notificationManager = appNotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            onUpdateNotificationChannel()
        }
        isAttached = true
    }

    @CallSuper
    open fun onDetach() {
        isAttached = false
        notificationManager = null
    }

    /**
     * Called when notification was pressed.
     *
     * @param notificationId Pressed notification id.
     */
    open fun onContentPressed(notificationId: Int) {
        // No-op
    }

    /**
     * Called when notification is removed by swiping to any side.
     *
     * @param notificationId Deleted notification id.
     */
    open fun onDeleted(notificationId: Int) {
        // No-op
    }

    /**
     * Adds a local notification.
     */
    protected fun notify(
        notificationId: Int,
        builder: NotificationBuilder
    ) {
        notificationManager?.notify(
            tag,
            notificationId,
            channel,
            PendingIntent.FLAG_CANCEL_CURRENT,
            builder
        )
    }

    /**
     * Clear a notification with provided id.
     */
    protected fun cancel(notificationId: Int) {
        notificationManager?.cancel(tag, notificationId)
    }
}
