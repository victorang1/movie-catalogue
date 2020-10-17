package com.example.moviecatalogue.di

import com.example.moviecatalogue.di.modules.appModule
import com.example.moviecatalogue.di.modules.viewModelModule
import org.koin.core.module.Module

val appComponent: List<Module> = listOf(
    appModule,
    viewModelModule
)