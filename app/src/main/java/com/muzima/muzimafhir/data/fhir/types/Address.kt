package com.muzima.muzimafhir.data.fhir.types

data class Address(
    var use: String? = null,
    var type: String? = null,
    var text: String? = null,
    var line: MutableList<String>? = mutableListOf(),
    var city: String? = null,
    var district: String? = null,
    var state: String? = null,
    var postalCode: String? = null,
    var country: String? = null,
    var period: Period? = null
) {
    override fun toString(): String {
        return "Address(use=$use, type=$type, text=$text, line=$line, city=$city, district=$district, state=$state, postalCode=$postalCode, country=$country, period=$period)"
    }

    fun toMap() : MutableMap<String?, String?> {
        return mutableMapOf(
                "use" to use,
                "type" to type,
                "text" to text,
                "city" to city,
                "district" to district,
                "state" to state,
                "postalCode" to postalCode,
                "country" to country
        )
    }
}