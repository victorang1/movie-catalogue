package com.example.moviecatalogue.di

import com.example.moviecatalogue.di.modules.*
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    apiModule,
    appModule,
    databaseModule,
    repositoryModule,
    viewModelModule
)