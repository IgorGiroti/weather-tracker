package com.igorgiroti.weathertracker.di

import org.koin.dsl.module

object AppModule {
    val appModule = module {
        single {
            //TODO NETWORKING
        }
        single {
            //TODO REPOSITORY
        }
        single {
            //TODO USECASE
        }

        // viewModel {
        //TODO ViewModel
        //   }
    }
}

