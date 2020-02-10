package com.muzima.muzimafhir.data.types

data class Address(
    var use: String? = null,
    var type: String? = null,
    var text: String? = null,
    var line: MutableList<String>? = null,
    var city: String? = null,
    var district: String? = null,
    var state: String? = null,
    var postalCode: String? = null,
    var country: String? = null,
    var period: Period? = null
)