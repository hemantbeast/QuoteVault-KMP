package com.andronerds.quotes.di

import com.andronerds.quotes.data.repositories.QuoteRepositoryImpl
import com.andronerds.quotes.domain.repositories.QuoteRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<QuoteRepository> { QuoteRepositoryImpl(get()) }
}
