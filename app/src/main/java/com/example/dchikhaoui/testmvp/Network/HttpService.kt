package com.example.dchikhaoui.testmvp.Network


import com.example.dchikhaoui.testmvp.BuildConfig
import okhttp3.OkHttpClient


import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class HttpService {
    fun getClient(): HttpInterface {
        val httpClient = OkHttpClient
                .Builder()
        val builder = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BuildConfig.SPORT_WS_DOMAIN)

        val retrofit = builder
                .client(httpClient.build())
                .build()

        return retrofit.create(HttpInterface::class.java);
    }
}