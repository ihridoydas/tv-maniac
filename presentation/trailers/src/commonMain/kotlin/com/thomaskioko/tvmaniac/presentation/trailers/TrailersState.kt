package com.thomaskioko.tvmaniac.presentation.trailers

import com.thomaskioko.tvmaniac.presentation.trailers.model.Trailer

interface TrailersState

object LoadingTrailers : TrailersState

data class TrailersContent(
    val selectedVideoKey: String? = null,
    val trailersList: List<Trailer> = emptyList(),
) : TrailersState

data class TrailerError(val errorMessage: String) : TrailersState
