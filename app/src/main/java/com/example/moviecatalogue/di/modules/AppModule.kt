package com.example.moviecatalogue.di.modules

import android.content.Context
import android.content.res.Resources
import com.example.moviecatalogue.data.service.ApiConfig
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(ApiConfig.BASE_URL)
            .build()
    }

    fun providesResources(context: Context): Resources = context.resources

    single { providesRetrofit() }
    single { providesResources(androidContext()) }
}