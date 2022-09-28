package com.oyamo.sinema.data.repository

import com.oyamo.sinema.data.remote.TMDBApi
import com.oyamo.sinema.data.remote.responses.CreditsResponse
import com.oyamo.sinema.data.remote.responses.MovieDetails
import com.oyamo.sinema.data.remote.responses.TvSeriesDetails
import com.oyamo.sinema.util.Resource
import timber.log.Timber
import javax.inject.Inject

class FilmsDetailsRepository @Inject constructor(private val api: TMDBApi) {

    // Movie Details
    suspend fun getMoviesDetails(movieId: Int): Resource<MovieDetails> {
        val response = try {
            api.getMovieDetails(movieId)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Movie details: $response")
        return Resource.Success(response)
    }

    // Movie Casts
    suspend fun getMovieCasts(movieId: Int): Resource<CreditsResponse> {
        val response = try {
            api.getMovieCredits(movieId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Movie Casts $response")
        return Resource.Success(response)
    }

    // Series Details
    suspend fun getTvSeriesDetails(tvId: Int): Resource<TvSeriesDetails> {
        val response = try {
            api.getTvSeriesDetails(tvId)
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        Timber.d("Series details: $response")
        return Resource.Success(response)
    }

    // Series Casts
    suspend fun getTvSeriesCasts(tvId: Int): Resource<CreditsResponse> {
        val response = try {
            api.getTvSeriesCredits(tvId)
        } catch (e: Exception) {
            Timber.d(e.message)
            return Resource.Error("Unknown error occurred")
        }

        Timber.d("Series casts $response")
        return Resource.Success(response)
    }
}