package com.infiniteset.notifications.modulemvp

import com.infiniteset.notifications.presenter.BasePresenter
import com.infiniteset.notifications.presenter.BaseView

class SampleMvpNotificationPresenter : BasePresenter<SampleView>() {

    private var counter = 0

    override fun attachView(view: SampleView) {
        super.attachView(view)
        updateView()
    }

    fun processClick() {
        counter++
        updateView()
    }

    private fun updateView() {
        ifViewAttached { view ->
            if (counter % 2 == 0) {
                view.onShowOddText()
            } else {
                view.onShowEvenText()
            }
        }
    }

    override fun detachView() {
        counter = 0
        super.detachView()
    }
}

interface SampleView : BaseView {

    fun onShowEvenText()

    fun onShowOddText()
}