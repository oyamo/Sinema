package com.oyamo.sinema.screens.film_details.series

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import com.ramcosta.composedestinations.annotation.Destination
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.oyamo.sinema.data.remote.responses.CreditsResponse
import com.oyamo.sinema.data.remote.responses.TvSeriesDetails
import com.oyamo.sinema.screens.favorites.FavoritesViewModel
import com.oyamo.sinema.screens.film_details.FilmDetailsViewModel
import com.oyamo.sinema.screens.film_details.common.FilmImageBanner
import com.oyamo.sinema.screens.film_details.common.FilmInfo
import com.oyamo.sinema.util.Constants
import com.oyamo.sinema.util.Resource
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@OptIn(ExperimentalAnimationApi::class, androidx.compose.ui.ExperimentalComposeUiApi::class)
@Destination
@Composable
fun TvSeriesDetailsScreen(
    filmId: Int,
    navigator: DestinationsNavigator,
    viewModel: FilmDetailsViewModel = hiltViewModel(),
    favoritesViewModel: FavoritesViewModel = hiltViewModel()
) {
    val scrollState = rememberLazyListState()

    val details = produceState<Resource<TvSeriesDetails>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvSeriesDetails(filmId)
    }.value

    val casts = produceState<Resource<CreditsResponse>>(initialValue = Resource.Loading()) {
        value = viewModel.getTvSeriesCasts(filmId)
    }.value

    // Include Film Genres

    Box {
        if (details is Resource.Success) {
            FilmInfo(
                scrollState = scrollState,
                overview = details.data?.overview.toString(),
                releaseDate = details.data?.firstAirDate.toString(),
                navigator = navigator,
                casts = casts
            )
            FilmImageBanner(
                scrollState = scrollState,
                posterUrl = "${Constants.IMAGE_BASE_UR}/${details.data?.posterPath}",
                filmName = details.data?.name.toString(),
                filmId = details.data?.id!!,
                filmType = "tv",
                releaseDate = details.data.firstAirDate,
                rating = details.data.voteAverage.toFloat(),
                navigator = navigator,
                viewModel = favoritesViewModel
            )
        } else {
            CircularProgressIndicator()
        }
    }
}