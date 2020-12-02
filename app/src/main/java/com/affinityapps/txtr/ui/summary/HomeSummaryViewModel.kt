package com.affinityapps.txtr.ui.summary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affinityapps.txtr.ui.home.Messages

class HomeSummaryViewModel : ViewModel() {

    val message = MutableLiveData<String>()
    val time = MutableLiveData<String>()
    val date = MutableLiveData<String>()

    fun messageText(input: String){
        message.value = input
    }

    fun messageTime(input: String){
        time.value = input
    }

    fun messageDate(input: String){
        date.value = input
    }
}