package com.andronerds.quotes.di

import com.andronerds.quotes.presentation.ui.quote.viewModels.QuoteViewModel
import org.koin.dsl.module

val appModule = module {
    factory { QuoteViewModel(get()) }
}
