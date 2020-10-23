package com.affinityapps.txtr.ui.graphs

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class StatisticsViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is a stats fragment test"
    }
    val text: LiveData<String> = _text
}