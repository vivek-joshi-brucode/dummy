package com.example.dummy.hiltmodule

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.dummy.network.apiservice.APIService
import com.example.dummy.repository.repo.AppRepositoryImpl
import com.example.dummy.room.AppDao
import com.example.dummy.room.database.AppDatabase
import com.example.dummy.utils.Constants.BASE_URL_OBJECTS
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

// Hilt module provides objects
@Module
@InstallIn(SingletonComponent::class)
object HiltModuleClass {

    @Provides
    fun provideBaseUrl(): String = BASE_URL_OBJECTS

    @Provides
    @Singleton
    fun provideRetrofit(baseUrl: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): APIService = retrofit.create(APIService::class.java)


    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "app_db").build()
    }

    @Provides
    fun provideAppDao(database: AppDatabase): AppDao = database.getAppDao()

    @Provides
    @Singleton
    fun provideAppRepositoryImpl(apiService: APIService, appDao: AppDao) =
        AppRepositoryImpl(apiService = apiService, appDao = appDao)

}