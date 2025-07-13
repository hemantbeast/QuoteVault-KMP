package com.andronerds.quotes.di

import com.andronerds.quotes.data.HttpService
import com.andronerds.quotes.data.services.QuoteService
import com.andronerds.quotes.data.services.QuoteServiceImpl
import org.koin.dsl.module

val serviceModule = module {
    single { HttpService() }

    single<QuoteService> { QuoteServiceImpl(get()) }
}
