package com.infiniteset.notifications.manager

import com.infiniteset.notifications.notification.NotificationBuilder

interface NotificationManager {

    /**
     * Notifies notification with provided [tag] and [notificationId].
     */
    fun notify(
        tag: String?,
        notificationId: Int,
        channel: String,
        flag: Int,
        builder: NotificationBuilder
    )

    /**
     * Cancels notification with provided [tag] and [notificationId].
     */
    fun cancel(tag: String?, notificationId: Int)
}
