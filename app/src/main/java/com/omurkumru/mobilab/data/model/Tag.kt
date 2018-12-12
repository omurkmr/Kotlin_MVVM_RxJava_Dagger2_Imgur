package com.omurkumru.mobilab.data.model

import com.squareup.moshi.Json


class Tag {

    @Json(name = "name")
    var name: String? = null
    @Json(name = "display_name")
    var displayName: String? = null
    @Json(name = "followers")
    var followers: Int? = null
    @Json(name = "total_items")
    var totalItems: Int? = null
    @Json(name = "following")
    var following: Boolean? = null
    @Json(name = "background_hash")
    var backgroundHash: String? = null
    @Json(name = "thumbnail_hash")
    var thumbnailHash: Any? = null
    @Json(name = "accent")
    var accent: String? = null
    @Json(name = "background_is_animated")
    var backgroundIsAnimated: Boolean? = null
    @Json(name = "thumbnail_is_animated")
    var thumbnailIsAnimated: Boolean? = null
    @Json(name = "is_promoted")
    var isPromoted: Boolean? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "logo_hash")
    var logoHash: Any? = null
    @Json(name = "logo_destination_url")
    var logoDestinationUrl: Any? = null
    @Json(name = "description_annotations")
    var descriptionAnnotations: DescriptionAnnotations? = null

}