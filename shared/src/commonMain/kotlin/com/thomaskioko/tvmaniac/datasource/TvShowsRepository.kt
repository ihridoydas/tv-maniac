package com.thomaskioko.tvmaniac.datasource

import com.thomaskioko.tvmaniac.datasource.cache.TvShowsEntity

interface TvShowsRepository  {

    suspend fun getPopularTvShows(page: Int) : List<TvShowsEntity>

    suspend fun getTopRatedTvShows(page: Int) : List<TvShowsEntity>
}