package com.affinityapps.txtr.ui.home

data class ContactsData(var name: String, var number: String) {

    constructor(date: String, time: String, message:String): this(date, time)
}