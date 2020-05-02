package com.nkr.vumobile.network

import com.nkr.vumobile.model.UserSearchResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NetworkApi {


   // @GET("/search/users")
    @GET("users")
    fun searchUser(@Query("page") page_num : Int

    ): Observable<UserSearchResponse>



}