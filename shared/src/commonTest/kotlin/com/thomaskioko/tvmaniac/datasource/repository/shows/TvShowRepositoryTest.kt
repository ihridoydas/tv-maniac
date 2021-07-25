package com.thomaskioko.tvmaniac.datasource.repository.shows

import com.thomaskioko.tvmaniac.MockData.getTvResponse
import com.thomaskioko.tvmaniac.MockData.makeTvShowEntityList
import com.thomaskioko.tvmaniac.MockData.tvShowSeasonEntity
import com.thomaskioko.tvmaniac.datasource.cache.shows.TvShowCache
import com.thomaskioko.tvmaniac.datasource.enums.TimeWindow.WEEK
import com.thomaskioko.tvmaniac.datasource.enums.TvShowCategory
import com.thomaskioko.tvmaniac.datasource.network.api.TvShowsService
import com.thomaskioko.tvmaniac.datasource.repository.tvshow.TvShowsRepositoryImpl
import com.thomaskioko.tvmaniac.util.runBlocking
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.mockk.unmockkAll
import io.mockk.verify
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test


internal class TvShowRepositoryTest {


    @RelaxedMockK
    lateinit var apiService: TvShowsService

    @RelaxedMockK
    lateinit var cache: TvShowCache

    private lateinit var repository: TvShowsRepositoryImpl

    @BeforeTest
    fun setUp() {
        MockKAnnotations.init(this)
        repository = TvShowsRepositoryImpl(apiService, cache)
    }

    @AfterTest
    fun tearDownAll() {
        unmockkAll()
    }

    @Test
    fun givenDataIsCached_thenGetTvShowIsLoadedFromCache() = runBlocking {
        every { cache.getTvShow(84958) } returns tvShowSeasonEntity

        repository.getTvShow(84958)

        verify(exactly = 0) {
            runBlocking { apiService.getTvShowDetails(1) }
        }

        verify { cache.getTvShow(84958) }
    }

    @Test
    fun givenDataIsCached_thenDataIsLoadedFromCache() {
        every { cache.getTvShows() } returns makeTvShowEntityList()

        runBlocking {
            repository.getPopularTvShows(1)
        }

        verify(exactly = 0) {

            runBlocking { apiService.getPopularShows(1) }
            cache.insert(makeTvShowEntityList())
        }

        verify(exactly = 2) { cache.getTvShows() }
    }

    @Test
    fun givenDataIsNotCached_thenApiServiceIsInvoked_AndDataIsLoadedFromCache() = runBlocking {
        coEvery { apiService.getPopularShows(1) } answers { getTvResponse() }

        repository.getPopularTvShows(1)

        verify {
            runBlocking { apiService.getPopularShows(1) }
            cache.getTvShows()
        }
    }

    @Test
    fun givenDataIsNotCached_thenGetTrendingShowsInvokesApiService_AndDataIsLoadedFromCache() = runBlocking {
        coEvery { apiService.getTrendingShows(WEEK.window) } answers { getTvResponse() }

        repository.getTrendingShows(WEEK.window)

        verify {
            runBlocking { apiService.getTrendingShows(WEEK.window) }
            cache.getTvShows(TvShowCategory.TRENDING, WEEK)
        }
    }

}