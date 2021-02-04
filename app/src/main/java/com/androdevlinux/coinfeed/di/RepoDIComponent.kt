package com.androdevlinux.coinfeed.di

import com.androdevlinux.coinfeed.repository.TickerRepository
import org.koin.dsl.module

/**
 * Repository DI module.
 * Provides Repo dependency.
 */
val RepoDependency = module {
    factory {
        TickerRepository()
    }
}