package com.oyamo.sinema.di

import android.app.Application
import androidx.room.Room
import com.oyamo.sinema.data.local.FavoritesDatabase
import com.oyamo.sinema.data.remote.TMDBApi
import com.oyamo.sinema.data.repository.*
import com.oyamo.sinema.util.Constants.BASE_URL
import com.oyamo.sinema.util.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .callTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)

        return okHttpClient.build()
    }

    @Provides
    @Singleton
    fun providesTMDBApi(okHttpClient: OkHttpClient): TMDBApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
            .create(TMDBApi::class.java)
    }

    @Provides
    @Singleton
    fun provideFavoritesDatabase(application: Application): FavoritesDatabase {
        return Room.databaseBuilder(
            application.applicationContext,
            FavoritesDatabase::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    @Singleton
    fun provideFilmsDetailsRepository(api: TMDBApi) = FilmsDetailsRepository(api)

    @Provides
    @Singleton
    fun provideGenresRepository(api: TMDBApi) = GenresRepository(api)

    @Provides
    @Singleton
    fun provideMoviesRepository(api: TMDBApi) = MoviesRepository(api)

    @Provides
    @Singleton
    fun provideTvSeriesRepository(api: TMDBApi) = TvSeriesRepository(api)

    @Provides
    @Singleton
    fun provideSearchRepository(api: TMDBApi) = SearchRepository(api)
}