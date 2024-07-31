package com.example.moviez.repositary.tmdb

import com.example.moviez.dataModels.GenreList
import com.example.moviez.dataModels.KeywordListResponse
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.dataModels.SeriesSeasonDetails
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.network.ApiService
import com.example.moviez.utils.ResponseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitCancellation
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TmdbRepositoryImpl @Inject constructor(
    private val apiService: ApiService
): TmdbRepository {
    override fun getMovieGenreList(): Flow<ResponseModel<GenreList>> = flow{
        emit(ResponseModel.Loading)

         try{
             val response = withContext( Dispatchers.IO) { apiService.getGenreListMovie() }
             emit(ResponseModel.Success(response))
         }
         catch (e: Exception){
             emit(ResponseModel.Error(e.message ?: "Something went wrong"))
         }
    }

    override fun getTvGenreList(): Flow<ResponseModel<GenreList>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getGenreListTv() }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getMovieListByGenre(
        genreId: Int,
        pageNo: Int
    ): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getMoviesWithGenre(genreId, pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTvListByGenre(genreId: Int, pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSeriesWithGenre(genreId, pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getNowPlayingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getNowPlayingMovies(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getPopularMovies(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopRatedMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getTopRatedMovies(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getUpcomingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getUpcomingMovies(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> =  flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext(Dispatchers.IO) { apiService.getTrendingMovies(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> =  flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext(Dispatchers.IO) { apiService.getTrendingSeries(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getPopularSeries(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopRatedSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getTopRatedSeries(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getTrendingPerson(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getPopularPerson(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<ResponseModel<MovieDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getMovieDetails(movieId) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSeriesDetails(seriesId: Int): Flow<ResponseModel<SeriesDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSeriesDetails(seriesId) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSeriesSeasonNumbers(seriesId: Int): Flow<ResponseModel<SeriesForSeasons>> = flow {
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSeriesSeasonNumbers(seriesId) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSeriesSeasonDetails(
        seriesId: Int,
        seasonNo: Int
    ): Flow<ResponseModel<SeriesSeasonDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSeriesSeasonDetails(seriesId, seasonNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPersonDetails(personId: Int): Flow<ResponseModel<PersonDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getPersonDetails(personId) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSearchedKeywords(query: String): Flow<ResponseModel<KeywordListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSearchedKeywords(query) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSearchedMovies(
        query: String,
        pageNo: Int
    ): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSearchedMovies(query, pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSearchedSeries(
        query: String,
        pageNo: Int
    ): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSearchedSeries(query, pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSearchedPerson(
        query: String,
        pageNo: Int
    ): Flow<ResponseModel<PersonListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( Dispatchers.IO) { apiService.getSearchedPerson(query, pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }
}