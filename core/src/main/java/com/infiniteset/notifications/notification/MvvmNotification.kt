package com.infiniteset.notifications.notification

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.infiniteset.notifications.manager.NotificationManager

/**
 * Base MVVM notification.
 */
abstract class MvvmNotification(isForeground: Boolean = false) :
    BaseNotification(isForeground), LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle() = lifecycleRegistry

    override fun onAttach(notificationManager: NotificationManager) {
        super.onAttach(notificationManager)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_START)
    }

    override fun onDetach() {
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        super.onDetach()
    }
}
