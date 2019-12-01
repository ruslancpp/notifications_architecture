package com.infiniteset.notifications.manager

import android.content.Context
import android.content.Intent
import com.infiniteset.notifications.notification.BaseNotification
import com.infiniteset.notifications.notification.NotificationBuilder

/**
 * A common interface for managing local notifications. Implementation of this interface should hide
 * a complexity of handling notification pressed/deleted events
 */
interface AppNotificationManager : NotificationManager {

    /**
     * Intercepts user's interaction with notification and delivers [BaseNotification] sender.
     *
     * @param context Notification manager's receives context.
     * @param intent An intent that was added to [PendingIntent] when notification was posted.
     */
    fun onInterceptAction(context: Context, intent: Intent)

    /**
     * Called when OS local has been changed.
     */
    fun onSystemLocalChanged()

    /**
     * Attaches all notifications and make all registered later notifications be attached on registration.
     */
    fun start()

    /**
     * Detaches all previously attached notifications and prevents attaching notifications registered later.
     */
    fun stop()

    /**
     * Registers [notification] in [AppNotificationManager]. If notification manager has been started before,
     * [notification] gets attached immediately on registration.
     */
    fun registerNotification(notification: BaseNotification)

    /**
     * Deregister [notification] from [AppNotificationManager] and detaches notification if was attached before.
     */
    fun deregisterNotification(notification: BaseNotification)
}
