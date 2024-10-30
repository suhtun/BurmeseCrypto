package org.su.thiri.di

import androidx.paging.Pager
import androidx.paging.PagingConfig
import org.su.thiri.coin.data.CoinRepositoryImpl
import org.su.thiri.coin.data.remote.CoinListPagingSource
import org.su.thiri.coin.presentation.coin_search.CoinSearchBarViewModel
import org.su.thiri.coin.domain.CoinRepository
import org.su.thiri.coin.presentation.coin_list.CoinListViewModel
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {


    factory { CoinListPagingSource(get()) }

    single {
        Pager(
            config = PagingConfig(pageSize = 20, prefetchDistance = 1, initialLoadSize = 1),
            pagingSourceFactory = { get<CoinListPagingSource>() }
        )
    }

    singleOf(::CoinRepositoryImpl).bind<CoinRepository>()

    viewModel {
        CoinListViewModel(get(), get())
    }

    viewModel {
        CoinSearchBarViewModel(get())
    }
}