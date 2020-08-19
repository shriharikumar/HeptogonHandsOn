package com.heptogontest.shrihari.model.service

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit
private const val BASE_URL = "https://www.json-generator.com/api/json/"
class NetworkInstance {

    init {

        println("Network Instance Invoked..")
    }

    companion object {

        var instance: Retrofit? = null
        fun getRetroInstance(): Retrofit? {

            val client = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .build()

            instance = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addCallAdapterFactory(
                    RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

            return instance
        }
    }

}