package com.androdevlinux.coinfeed.di

/**
 * Main dependency component.
 * This will create and provide required dependencies with sub dependencies.
 */
val appComponent = listOf(NetworkDependency, RepoDependency)