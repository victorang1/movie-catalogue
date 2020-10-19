package com.example.moviecatalogue.di

import com.example.moviecatalogue.di.modules.apiModule
import com.example.moviecatalogue.di.modules.appModule
import com.example.moviecatalogue.di.modules.repositoryModule
import com.example.moviecatalogue.di.modules.viewModelModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    apiModule,
    appModule,
    repositoryModule,
    viewModelModule
)