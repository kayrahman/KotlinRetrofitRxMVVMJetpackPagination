package com.nkr.vumobile.network

import com.facebook.stetho.okhttp3.StethoInterceptor
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object NetworkModule {
    private var retrofit: Retrofit? = null

    /**
     * Generate global static Retrofit
     *
     * @return
     */
    private val apiClient: Retrofit?
        get() {
            if (retrofit == null) {
                val okHttpClient = OkHttpClient.Builder()
                        .addNetworkInterceptor(StethoInterceptor())
                        .build()

                retrofit = Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                        .build()
            }
            return retrofit
        }

    /**
     * Get the interface class for API calling
     *
     * @return
     */
    val networkApi: NetworkApi
        get() {
            val r = apiClient
            return r?.create(NetworkApi::class.java)!!
        }



}
