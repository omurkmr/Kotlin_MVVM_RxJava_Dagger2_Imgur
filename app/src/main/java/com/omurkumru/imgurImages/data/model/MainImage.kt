package com.omurkumru.imgurImages.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainImage(var id: String?, var url: String?, var title: String?, var description: String?,
                     var upVotes: Int?, var downVotes: Int?, var score: Int?) : Parcelable