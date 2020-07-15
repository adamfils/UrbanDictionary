package com.techguy.urbandictionary.modules

import com.techguy.urbandictionary.model.SearchModel
import com.techguy.urbandictionary.viewmodel.SearchViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    factory { SearchModel("", 0L, 0L) }
}

val viewModule: Module = module {
    viewModel { SearchViewModel() }
}