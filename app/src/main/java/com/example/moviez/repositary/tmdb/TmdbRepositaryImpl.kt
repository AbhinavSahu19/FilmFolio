package com.example.moviez.repositary.tmdb

import android.provider.ContactsContract.Data
import android.util.Log
import com.example.moviez.dataModels.GenreList
import com.example.moviez.dataModels.KeywordListResponse
import com.example.moviez.dataModels.MovieDetails
import com.example.moviez.dataModels.MovieListResponse
import com.example.moviez.dataModels.PersonDetails
import com.example.moviez.dataModels.PersonListResponse
import com.example.moviez.dataModels.SeriesDetails
import com.example.moviez.dataModels.SeriesForSeasons
import com.example.moviez.dataModels.SeriesListResponse
import com.example.moviez.dataModels.SeriesSeasonDetails
import com.example.moviez.network.ApiService
import com.example.moviez.network.DataCache
import com.example.moviez.utils.ResponseModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import javax.inject.Inject

class TmdbRepositoryImpl @Inject constructor(
    private val coroutineDispatcher: CoroutineDispatcher,
    private val apiService: ApiService
): TmdbRepository {
    override fun getMovieGenreList(): Flow<ResponseModel<GenreList>> = flow{
        emit(ResponseModel.Loading)

         try{
             val cacheResponse = DataCache.GenreListCache[0]
             if(cacheResponse != null){
                 Log.i("Cache Call", "Cache Call made for movie genre list")
                 emit(ResponseModel.Success(cacheResponse))
             }
             else{
                 val response = withContext( coroutineDispatcher){ apiService.getGenreListMovie() }
                 Log.i("Network Call", "Network Call Made for movie genre list")
                 DataCache.GenreListCache[0] = response
                 emit(ResponseModel.Success(response))
             }
         }
         catch (e: Exception){
             emit(ResponseModel.Error(e.message ?: "Something went wrong"))
         }
    }

    override fun getTvGenreList(): Flow<ResponseModel<GenreList>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.GenreListCache[1]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for series genre list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher){ apiService.getGenreListTv() }
                Log.i("Network Call", "Network Call Made for series genre list")
                DataCache.GenreListCache[1] = response
                emit(ResponseModel.Success(response))
            }
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
            val cacheResponse = DataCache.MovieListResponseCache["G $genreId + $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for genre movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher){ apiService.getMoviesWithGenre(genreId, pageNo) }
                Log.i("Network Call", "Network Call made for genre movie list")
                DataCache.MovieListResponseCache["G $genreId + $pageNo"] = response
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTvListByGenre(genreId: Int, pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeriesListResponseCache["G $genreId + $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for genre series list")
                emit(ResponseModel.Success(cacheResponse))
            }
           else{
                val response = withContext( coroutineDispatcher){ apiService.getSeriesWithGenre(genreId, pageNo) }
                DataCache.SeriesListResponseCache["G $genreId + $pageNo"] = response
                Log.i("Network Call", "Network Call made for genre series list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getNowPlayingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.MovieListResponseCache["NowPlaying $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for now playing movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher){ apiService.getNowPlayingMovies(pageNo = pageNo) }
                DataCache.MovieListResponseCache["NowPlaying $pageNo"] = response
                Log.i("Network Call", "Network Call made for now playing movie list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.MovieListResponseCache["Popular $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for popular movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getPopularMovies(pageNo = pageNo) }
                DataCache.MovieListResponseCache["Popular $pageNo"] = response
                Log.i("Network Call", "Network Call made for popular movie list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopRatedMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.MovieListResponseCache["TopRated $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for top rated movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getTopRatedMovies(pageNo = pageNo) }
                DataCache.MovieListResponseCache["TopRated $pageNo"] = response
                Log.i("Network Call", "Network Call made for top rated movie list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getUpcomingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.MovieListResponseCache["Upcoming $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for upcoming movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getUpcomingMovies(pageNo = pageNo) }
                DataCache.MovieListResponseCache["Upcoming $pageNo"] = response
                Log.i("Network Call", "Network Call made for upcoming movie list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingMovies(pageNo: Int): Flow<ResponseModel<MovieListResponse>> =  flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.MovieListResponseCache["Trending $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for trending movie list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getTrendingMovies(pageNo = pageNo) }
                DataCache.MovieListResponseCache["Trending $pageNo"] = response
                Log.i("Network Call", "Network Call made for trending movie list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> =  flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeriesListResponseCache["Trending $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for trending series list")
                emit(ResponseModel.Success(cacheResponse))
            }
           else{
                val response = withContext( coroutineDispatcher) { apiService.getTrendingSeries(pageNo = pageNo) }
                DataCache.SeriesListResponseCache["Trending $pageNo"] = response
                Log.i("Network Call", "Network Call made for trending series list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeriesListResponseCache["Popular $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for popular series list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getPopularSeries(pageNo = pageNo) }
                DataCache.SeriesListResponseCache["Popular $pageNo"] = response
                Log.i("Network Call", "Network Call made for popular series list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTopRatedSeries(pageNo: Int): Flow<ResponseModel<SeriesListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeriesListResponseCache["TopRated $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for top rated series list")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher) { apiService.getTopRatedSeries(pageNo = pageNo) }
                DataCache.SeriesListResponseCache["TopRated $pageNo"] = response
                Log.i("Network Call", "Network Call made for top rated series list")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getTrendingPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext( coroutineDispatcher) { apiService.getTrendingPerson(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPopularPerson(pageNo: Int): Flow<ResponseModel<PersonListResponse>> = flow{
        emit(ResponseModel.Loading)

        try{
            val response = withContext(coroutineDispatcher) { apiService.getPopularPerson(pageNo = pageNo) }
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<ResponseModel<MovieDetails>> = flow{
        emit(ResponseModel.Loading)
        try{
            val cacheResponse = DataCache.MovieDetailCache[movieId]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for movie details")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( coroutineDispatcher){apiService.getMovieDetails(movieId)}
                DataCache.MovieDetailCache[movieId] = response
                Log.i("Network Call", "Network Call made for movie details")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }


    override fun getSeriesDetails(seriesId: Int): Flow<ResponseModel<SeriesDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeriesDetailsCache[seriesId]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for series details")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( Dispatchers.IO) { apiService.getSeriesDetails(seriesId) }
                DataCache.SeriesDetailsCache[seriesId] = response
                Log.i("Network Call", "Network Call made for series details")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getSeriesSeasonNumbers(seriesId: Int): Flow<ResponseModel<SeriesForSeasons>> = flow {
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.SeasonNumbersCache[seriesId]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for season numbers for series")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( Dispatchers.IO) { apiService.getSeriesSeasonNumbers(seriesId) }
                DataCache.SeasonNumbersCache[seriesId] = response
                Log.i("Network Call", "Network Call made for season number for series")
                emit(ResponseModel.Success(response))
            }
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
            val cacheResponse = DataCache.SeasonDetailsCache["$seriesId + $seasonNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for season details of a series")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( Dispatchers.IO) { apiService.getSeriesSeasonDetails(seriesId, seasonNo) }
                DataCache.SeasonDetailsCache["$seriesId + $seasonNo"] = response
                Log.i("Network Call", "Network Call made for season details of series")
                emit(ResponseModel.Success(response))
            }
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }

    override fun getPersonDetails(personId: Int): Flow<ResponseModel<PersonDetails>> = flow{
        emit(ResponseModel.Loading)

        try{
            val cacheResponse = DataCache.PersonDetailsCache[personId]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for person details")
                emit(ResponseModel.Success(cacheResponse))
            }
            else{
                val response = withContext( Dispatchers.IO) { apiService.getPersonDetails(personId) }
                DataCache.PersonDetailsCache[personId] = response
                Log.i("Network Call", "Network Call made for person details")
                emit(ResponseModel.Success(response))
            }
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
            val cacheResponse = DataCache.MovieListResponseCache["S $query + $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for searched movies")
                emit(ResponseModel.Success(cacheResponse))
            }
            val response = withContext( Dispatchers.IO) { apiService.getSearchedMovies(query, pageNo) }
            DataCache.MovieListResponseCache["S $query + $pageNo"] = response
            Log.i("Network Call", "Network Call made for searched movies")
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
            val cacheResponse = DataCache.SeriesListResponseCache["S $query + $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for searched series")
                emit(ResponseModel.Success(cacheResponse))
            }
            val response = withContext( Dispatchers.IO) { apiService.getSearchedSeries(query, pageNo) }
            DataCache.SeriesListResponseCache["S $query + $pageNo"] = response
            Log.i("Network Call", "Network Call made for searched series")
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
            val cacheResponse = DataCache.PersonListResponseCache["S $query + $pageNo"]
            if(cacheResponse != null){
                Log.i("Cache Call", "Cache Call made for searched person")
                emit(ResponseModel.Success(cacheResponse))
            }
            val response = withContext( Dispatchers.IO) { apiService.getSearchedPerson(query, pageNo) }
            DataCache.PersonListResponseCache["S $query + $pageNo"] = response
            Log.i("Network Call", "Network Call made for searched person")
            emit(ResponseModel.Success(response))
        }
        catch (e: Exception){
            emit(ResponseModel.Error(e.message ?: "Something went wrong"))
        }
    }
}