package com.oyamo.sinema.screens.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.filter
import com.oyamo.sinema.data.repository.GenresRepository
import com.oyamo.sinema.data.repository.MoviesRepository
import com.oyamo.sinema.data.repository.TvSeriesRepository
import com.oyamo.sinema.model.Genre
import com.oyamo.sinema.model.Movie
import com.oyamo.sinema.model.Series
import com.oyamo.sinema.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val moviesRepository: MoviesRepository,
    private val seriesRepository: TvSeriesRepository,
    private val genresRepository: GenresRepository
) : ViewModel() {
    private val _selectedOption = mutableStateOf("Movies")
    val selectedOption: State<String> = _selectedOption

    private val _selectedGenre = mutableStateOf("")
    val selectedGenre: State<String> = _selectedGenre

    fun setSelectedOption(selectedOption: String) {
        _selectedOption.value = selectedOption
    }

    fun setGenre(genre: String) {
        _selectedGenre.value = genre
    }

    /**
     * Movies states
     */
    private var _trendingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val trendingMovies: State<Flow<PagingData<Movie>>> = _trendingMovies

    private val _upcomingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val upcomingMovies: State<Flow<PagingData<Movie>>> = _upcomingMovies

    private val _topRatedMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val topRatedMovies: State<Flow<PagingData<Movie>>> = _topRatedMovies

    private val _nowPlayingMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val nowPlayingMovies: State<Flow<PagingData<Movie>>> = _nowPlayingMovies

    private val _popularMovies = mutableStateOf<Flow<PagingData<Movie>>>(emptyFlow())
    val popularMovies: State<Flow<PagingData<Movie>>> = _popularMovies

    private val _moviesGenres = mutableStateOf<List<Genre>>(emptyList())
    val moviesGenres: State<List<Genre>> = _moviesGenres


    /**
     * Tv Series states
     */
    private val _trendingTvSeries = mutableStateOf<Flow<PagingData<Series>>>(emptyFlow())
    val trendingTvSeries: State<Flow<PagingData<Series>>> = _trendingTvSeries

    private val _onAirTvSeries = mutableStateOf<Flow<PagingData<Series>>>(emptyFlow())
    val onAirTvSeries: State<Flow<PagingData<Series>>> = _onAirTvSeries

    private val _topRatedTvSeries = mutableStateOf<Flow<PagingData<Series>>>(emptyFlow())
    val topRatedTvSeries: State<Flow<PagingData<Series>>> = _topRatedTvSeries

    private val _airingTodayTvSeries = mutableStateOf<Flow<PagingData<Series>>>(emptyFlow())
    val airingTodayTvSeries: State<Flow<PagingData<Series>>> = _airingTodayTvSeries

    private val _popularTvSeries = mutableStateOf<Flow<PagingData<Series>>>(emptyFlow())
    val popularTvSeries: State<Flow<PagingData<Series>>> = _popularTvSeries

    private val _tvSeriesGenres = mutableStateOf<List<Genre>>(emptyList())
    val tvSeriesGenres: State<List<Genre>> = _tvSeriesGenres

    init {
        getTrendingMovies(null)
        getNowPayingMovies(null)
        getUpcomingMovies(null)
        getTopRatedMovies(null)
        getPopularMovies(null)
        getPopularTvSeries(null)
        getMoviesGenres()

        getAiringTodayTvSeries(null)
        getTrendingTvSeries(null)
        getOnTheAirTvSeries(null)
        getTopRatedTvSeries(null)
        getOnTheAirTvSeries(null)
        getSeriesGenres()
    }

    /**
     * Movies
     */
    fun getTrendingMovies(genreId: Int?) {
        viewModelScope.launch {
            _trendingMovies.value = if (genreId != null) {
                moviesRepository.getTrendingMoviesThisWeek().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTrendingMoviesThisWeek().cachedIn(viewModelScope)
            }
        }
    }


    fun getUpcomingMovies(genreId: Int?) {
        viewModelScope.launch {
            _upcomingMovies.value = if (genreId != null) {
                moviesRepository.getUpcomingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getUpcomingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedMovies(genreId: Int?) {
        viewModelScope.launch {
            _topRatedMovies.value = if (genreId != null) {
                moviesRepository.getTopRatedMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getTopRatedMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getNowPayingMovies(genreId: Int?) {
        viewModelScope.launch {
            _nowPlayingMovies.value = if (genreId != null) {
                moviesRepository.getNowPlayingMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getNowPlayingMovies().cachedIn(viewModelScope)
            }
        }
    }

    fun getPopularMovies(genreId: Int?) {
        viewModelScope.launch {
            _popularMovies.value = if (genreId != null) {
                moviesRepository.getPopularMovies().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                moviesRepository.getPopularMovies().cachedIn(viewModelScope)
            }
        }
    }

    private fun getMoviesGenres() {
        viewModelScope.launch {
            when (val result = genresRepository.getMoviesGenres()) {
                is Resource.Success -> {
                    _moviesGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {
                    //loadingError.value = result.message.toString()
                }
                else -> {}
            }
        }
    }

    /**
     * Tv Series
     */
    fun getTrendingTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _trendingTvSeries.value = if (genreId != null) {
                seriesRepository.getTrendingThisWeekTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getTrendingThisWeekTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getOnTheAirTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _onAirTvSeries.value = if (genreId != null) {
                seriesRepository.getOnTheAirTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getOnTheAirTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getTopRatedTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _topRatedTvSeries.value = if (genreId != null) {
                seriesRepository.getTopRatedTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getTopRatedTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getAiringTodayTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _airingTodayTvSeries.value = if (genreId != null) {
                seriesRepository.getAiringTodayTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getAiringTodayTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    fun getPopularTvSeries(genreId: Int?) {
        viewModelScope.launch {
            _popularTvSeries.value = if (genreId != null) {
                seriesRepository.getPopularTvSeries().map { pagingData ->
                    pagingData.filter {
                        it.genreIds.contains(genreId)
                    }
                }.cachedIn(viewModelScope)
            } else {
                seriesRepository.getPopularTvSeries().cachedIn(viewModelScope)
            }
        }
    }

    private fun getSeriesGenres() {
        viewModelScope.launch {
            when (val result = genresRepository.getSeriesGenres()) {
                is Resource.Success -> {
                    _tvSeriesGenres.value = result.data?.genres!!
                }
                is Resource.Error -> {
                    //loadingError.value = result.message.toString()
                }
                else -> {}
            }
        }
    }
}