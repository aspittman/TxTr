package com.affinityapps.txtr.ui.summary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.affinityapps.txtr.ui.home.Messages

class HomeSummaryViewModel : ViewModel() {

    private val message = MutableLiveData<Messages>()
    private val date = MutableLiveData<Messages>()

    fun messageBody(input: Messages){
        message.value = input
    }

    fun messageDate(input: Messages){
        date.value = input
    }
}