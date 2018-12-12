package com.omurkumru.mobilab.data.source.remote

import com.omurkumru.mobilab.data.model.RawGalleryResponse
import com.omurkumru.mobilab.utils.Constants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @Headers("Authorization: Client-ID ${Constants.CLIENT_ID}")
    @GET("gallery/{section}")
    fun getGallery(@Path("section") section: String, @Query("showViral") showViral: Boolean): Observable<RawGalleryResponse>
}