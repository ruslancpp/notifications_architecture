package com.infiniteset.notifications.notification

import android.content.Context
import com.infiniteset.notifications.manager.AppNotificationManager
import com.infiniteset.notifications.presenter.BasePresenter
import com.infiniteset.notifications.presenter.BaseView

/**
 * A base view for MVP notification.
 */
abstract class MvpNotification<V : BaseView, P : BasePresenter<V>>(
    context: Context,
    isForeground: Boolean = false
) : BaseNotification(context, isForeground) {

    protected lateinit var presenter: P
        private set

    protected abstract fun createPresenter(): P

    override fun onAttach(appNotificationManager: AppNotificationManager) {
        super.onAttach(appNotificationManager)
        presenter = createPresenter()
        @Suppress("UNCHECKED_CAST")
        presenter.attachView(this as V)
    }

    override fun onDetach() {
        presenter.detachView()
        super.onDetach()
    }
}