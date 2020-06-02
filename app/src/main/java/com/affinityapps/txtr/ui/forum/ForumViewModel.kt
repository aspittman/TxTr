package com.affinityapps.txtr.ui.forum

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ForumViewModel() : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is a forum fragment test"
    }
    val text: LiveData<String> = _text
}