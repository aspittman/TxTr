package com.affinityapps.txtr.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    val date = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val message = MutableLiveData<String>()

    fun dateTransfer(item: String) {
        date.value = item
    }

    fun timeTransfer(item: String) {
        time.value = item
    }

    fun messageTransfer(item: String) {
        message.value = item
    }
}
