package com.infiniteset.notifications.modulemvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SampleViewModel : ViewModel() {

    val counter = MutableLiveData<Int>().apply {
        value = 0
    }

    fun incrementCounter() {
        counter.value = counter.value!! + 1
    }
}
