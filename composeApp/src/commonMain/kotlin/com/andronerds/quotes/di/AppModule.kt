package com.andronerds.quotes.di

import com.andronerds.quotes.presentation.ui.quote.viewModels.QuoteViewModel
import com.andronerds.quotes.presentation.ui.saved.viewModels.SavedViewModel
import org.koin.dsl.module

val appModule = module {
    factory { QuoteViewModel(get()) }

    factory { SavedViewModel(get()) }
}
