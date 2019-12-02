package com.infiniteset.notifications.notification

import com.infiniteset.notifications.manager.NotificationManager
import com.infiniteset.notifications.presenter.BasePresenter
import com.infiniteset.notifications.presenter.BaseView

/**
 * A base view for MVP notification.
 */
abstract class MvpNotification<V : BaseView, P : BasePresenter<V>>(
    isForeground: Boolean = false
) : BaseNotification(isForeground) {

    protected lateinit var presenter: P
        private set

    protected abstract fun createPresenter(): P

    override fun onAttach(notificationManager: NotificationManager) {
        super.onAttach(notificationManager)
        presenter = createPresenter()
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }
}
