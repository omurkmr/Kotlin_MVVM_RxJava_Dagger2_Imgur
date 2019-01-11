package com.omurkumru.imgurImages.data.source.remote

import com.omurkumru.imgurImages.data.model.RawGalleryResponse
import com.omurkumru.imgurImages.utils.ImgurConstants
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query


interface ApiInterface {

    @Headers("Authorization: Client-ID ${ImgurConstants.CLIENT_ID}")
    @GET("gallery/{section}/{sort}/{window}")
    fun getGallery(@Path("section") section: String, @Path("sort") sort: String, @Path("window") window: String, @Query("showViral") showViral: Boolean): Observable<RawGalleryResponse>
}