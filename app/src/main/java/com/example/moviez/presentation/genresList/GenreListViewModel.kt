package com.example.moviez.presentation.genresList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.moviez.dataModels.GenreList
import com.example.moviez.navigation.NavigationDestination
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.utils.ResponseModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


object GenreListDestination: NavigationDestination{
    override val route = "genre_list"
}

@HiltViewModel
class GenreListViewModel @Inject constructor(
    private val repo: TmdbRepository
) : ViewModel(){
    private val _movieGenreList = MutableStateFlow<ResponseModel<GenreList>>(ResponseModel.Loading)
    val movieGenreList: StateFlow<ResponseModel<GenreList>> = _movieGenreList

    private val _tvGenreList = MutableStateFlow<ResponseModel<GenreList>>(ResponseModel.Loading)
    val tvGenreList: StateFlow<ResponseModel<GenreList>> = _tvGenreList

    init {
        getMovieGenreList()
    }

    fun getMovieGenreList(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getMovieGenreList().collect{
                withContext(Dispatchers.Main){ _movieGenreList.value = it }
            }
        }
    }
    fun getTvGenreList(){
        viewModelScope.launch(Dispatchers.IO){
            repo.getTvGenreList().collect{
                withContext(Dispatchers.Main){ _tvGenreList.value = it }
            }
        }
    }
}