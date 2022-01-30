package com.laughat.funnymemes.repository.remote.network


import com.laughat.funnymemes.base.models.MemesResponseDataModel
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface APIInterface {
    @GET("get_memes")
    fun getMemes(): Single<Response<MemesResponseDataModel>>

}