package com.omurkumru.imgurImages.data.model

import com.squareup.moshi.Json


class RawGalleryResponse {

    @Json(name = "data")
    var data: List<Datum>? = null
    @Json(name = "success")
    var success: Boolean? = null
    @Json(name = "status")
    var status: Int? = null

}