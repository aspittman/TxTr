package com.affinityapps.txtr.ui.graphs

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affinityapps.txtr.ui.home.Messages

class HomeGraphViewModel : ViewModel() {

    val message = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val date = MutableLiveData<String>()

    fun messageTextAmount(input: String){
        message.value = input
    }

    fun messageTime(input: String){
        time.value = input
    }

    fun messageDate(input: String){
        date.value = input
    }
}