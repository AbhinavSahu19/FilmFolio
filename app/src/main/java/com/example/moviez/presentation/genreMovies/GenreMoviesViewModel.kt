package com.example.moviez.presentation.genreMovies

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

object GenreMoviesDestination: NavigationDestination{
    override val route = "genre_movies"
    const val genreIdArgs = "genre_id"
    val routeWithArgs = "$route/{$genreIdArgs}"
}
@HiltViewModel
class GenreMoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repo: TmdbRepository
): ViewModel() {
    val genreId: Int = checkNotNull(savedStateHandle[GenreMoviesDestination.genreIdArgs])

    private val _moviesWithGenreResponse = MutableStateFlow<ResponseModel<MovieListResponse>>(ResponseModel.Loading)
    val moviesWithGenreResponse : StateFlow<ResponseModel<MovieListResponse>> = _moviesWithGenreResponse

    init {
        getMoviesWithGenre(1)
    }

    fun getMoviesWithGenre(pageNo: Int){
        viewModelScope.launch{
            repo.getMovieListByGenre(genreId, pageNo).collect{
                _moviesWithGenreResponse.value = it
            }
        }
    }
}