package com.affinityapps.txtr.ui.messages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affinityapps.txtr.ui.home.Messages

class HomeMessagesViewModel : ViewModel(){

    val message = MutableLiveData<Messages>()

    fun messageData(input: Messages){
        message.value = input
    }
}