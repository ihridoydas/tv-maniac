package com.thomaskioko.tvmaniac.tmdb.implementation.inject

import com.thomaskioko.tvmaniac.tmdb.api.ShowImageCache
import com.thomaskioko.tvmaniac.tmdb.api.TmdbRepository
import com.thomaskioko.tvmaniac.tmdb.api.TmdbService
import com.thomaskioko.tvmaniac.tmdb.implementation.ShowImageCacheImpl
import com.thomaskioko.tvmaniac.tmdb.implementation.TmdbRepositoryImpl
import com.thomaskioko.tvmaniac.tmdb.implementation.TmdbServiceImpl
import me.tatarka.inject.annotations.Provides

interface TmdbComponent {

    @Provides
    fun provideShowImageCache(bind: ShowImageCacheImpl): ShowImageCache = bind

    @Provides
    fun provideTmdbRepository(bind: TmdbRepositoryImpl): TmdbRepository = bind

    @Provides
    fun provideTmdbService(bind: TmdbServiceImpl): TmdbService = bind
}