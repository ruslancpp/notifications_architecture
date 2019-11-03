package com.infiniteset.notifications

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.infiniteset.notifications.modulemvp.SampleMvpNotification
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val notificationManager get() = (applicationContext as App).notificationManager

    private var mvpNotification: SampleMvpNotification? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        disable_notifications_switch.setOnCheckedChangeListener { view, isCheked ->
            if (isCheked) {
                notificationManager.start()
            } else {
                notificationManager.stop()
            }
        }

        mvp_switch.setOnCheckedChangeListener { view, isChecked ->
            mvpNotification?.let { notificationManager.deregisterNotification(it) }
            mvpNotification = if (isChecked) {
                SampleMvpNotification(applicationContext).also {
                    notificationManager.registerNotification(it)
                }
            } else {
                null
            }
        }

        mvp_switch.isChecked = false
        disable_notifications_switch.isChecked = false
    }
}
