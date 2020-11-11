package com.basri.mvvmandnotif.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


object RetroServer {


        private val retrofit: Retrofit

        private get(){
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val httpClient = OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .writeTimeout(60L, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build()
            return Retrofit.Builder() //.baseUrl("http://52.187.117.60/wisata_semarang/wisata/")
                .client(httpClient)
                .baseUrl("http://192.168.0.107:8070/lapor/lapor/")
                .addConverterFactory(GsonConverterFactory.create()) //                .client(client)
                .build()
        }

    @JvmStatic
    val apiServices: RestApi
        get() = retrofit.create(RestApi::class.java)




}