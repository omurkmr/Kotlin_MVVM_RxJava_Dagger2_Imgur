package com.omurkumru.mobilab.data.model

import com.squareup.moshi.Json


class Image {

    @Json(name = "id")
    var id: String? = null
    @Json(name = "title")
    var title: Any? = null
    @Json(name = "description")
    var description: String? = null
    @Json(name = "datetime")
    var datetime: Int? = null
    @Json(name = "type")
    var type: String? = null
    @Json(name = "animated")
    var animated: Boolean? = null
    @Json(name = "width")
    var width: Int? = null
    @Json(name = "height")
    var height: Int? = null
    @Json(name = "size")
    var size: Int? = null
    @Json(name = "views")
    var views: Int? = null
    @Json(name = "bandwidth")
    var bandwidth: Long? = null
    @Json(name = "vote")
    var vote: Any? = null
    @Json(name = "favorite")
    var favorite: Boolean? = null
    @Json(name = "nsfw")
    var nsfw: Any? = null
    @Json(name = "section")
    var section: Any? = null
    @Json(name = "account_url")
    var accountUrl: Any? = null
    @Json(name = "account_id")
    var accountId: Any? = null
    @Json(name = "is_ad")
    var isAd: Boolean? = null
    @Json(name = "in_most_viral")
    var inMostViral: Boolean? = null
    @Json(name = "has_sound")
    var hasSound: Boolean? = null
    @Json(name = "tags")
    var tags: List<Any>? = null
    @Json(name = "ad_type")
    var adType: Int? = null
    @Json(name = "ad_url")
    var adUrl: String? = null
    @Json(name = "in_gallery")
    var inGallery: Boolean? = null
    @Json(name = "link")
    var link: String? = null
    @Json(name = "comment_count")
    var commentCount: Any? = null
    @Json(name = "favorite_count")
    var favoriteCount: Any? = null
    @Json(name = "ups")
    var ups: Any? = null
    @Json(name = "downs")
    var downs: Any? = null
    @Json(name = "points")
    var points: Any? = null
    @Json(name = "score")
    var score: Any? = null

}