package com.infiniteset.notifications.presenter

import com.hannesdorfmann.mosby3.mvp.MvpBasePresenter
import com.hannesdorfmann.mosby3.mvp.MvpView

/**
 * Base notification presenter.
 */
abstract class BasePresenter<V : BaseView> : MvpBasePresenter<V>() {

    override fun detachView() {
        ifViewAttached { it.onClearNotifications() }
        super.detachView()
    }
}

/**
 * Base view contact for presenter.
 */
interface BaseView : MvpView {

    /**
     * Clears all notifications.
     */
    fun onClearNotifications()
}
