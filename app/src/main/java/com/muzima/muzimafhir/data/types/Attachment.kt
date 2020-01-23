package com.muzima.muzimafhir.data.types

import java.util.*

data class Attachment(
    var contentType: String? = null,
    var language: String? = null,
    var data: Base64? = null,
    var url: String? = null,
    var size: Int? = null,
    var hash: Base64? = null,
    var title: String? = null,
    var creation: Date? = null
)