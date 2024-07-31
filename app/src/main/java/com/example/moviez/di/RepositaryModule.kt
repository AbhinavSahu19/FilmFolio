package com.example.moviez.di

import com.example.moviez.repositary.auth.AuthRepository
import com.example.moviez.repositary.auth.AuthRepositoryImpl
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepository
import com.example.moviez.repositary.realtimeDatabase.RealtimeDbRepositoryImpl
import com.example.moviez.repositary.tmdb.TmdbRepository
import com.example.moviez.repositary.tmdb.TmdbRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun provideTmdbRepository(tmdbRepositoryImpl: TmdbRepositoryImpl): TmdbRepository


    @Binds
    abstract fun provideAuthRepository( authRepositoryImpl: AuthRepositoryImpl): AuthRepository

    @Binds
    abstract fun provideRealtimeRepository( realtimeDbRepositoryImpl: RealtimeDbRepositoryImpl): RealtimeDbRepository
}