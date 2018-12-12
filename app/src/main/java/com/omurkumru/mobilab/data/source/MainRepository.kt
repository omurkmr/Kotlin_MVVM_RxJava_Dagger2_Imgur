package com.omurkumru.mobilab.data.source

import com.omurkumru.mobilab.data.model.RawGalleryResponse
import com.omurkumru.mobilab.data.source.remote.ApiInterface
import com.omurkumru.mobilab.utils.Utils
import io.reactivex.Observable
import javax.inject.Inject


class MainRepository @Inject constructor(val apiInterface: ApiInterface, val utils: Utils) {

    fun getGalleryImagesFromApi(section: String = "hot", showViral: Boolean = true): Observable<RawGalleryResponse> {
        return apiInterface.getGallery(section, showViral)
    }

}