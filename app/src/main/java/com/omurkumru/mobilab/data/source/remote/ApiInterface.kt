package com.omurkumru.mobilab.data.source.remote

import io.reactivex.Observable
import retrofit2.http.GET


interface ApiInterface {

    @GET("")
    fun getData(): Observable<List<String>>
}