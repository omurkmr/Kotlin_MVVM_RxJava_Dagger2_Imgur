package com.omurkumru.imgurImages.data.source

import com.omurkumru.imgurImages.data.model.RawGalleryResponse
import com.omurkumru.imgurImages.data.source.remote.ApiInterface
import com.omurkumru.imgurImages.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class MainRepository @Inject constructor(val apiInterface: ApiInterface, val utils: Utils) {

    fun getGalleryImagesFromApi(section: String = "hot", sort: String = "viral", window: String = "day", showViral: Boolean = true): Observable<RawGalleryResponse> {
        return apiInterface.getGallery(section, sort, window, showViral)
    }

}