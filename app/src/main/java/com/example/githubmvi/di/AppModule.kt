package com.example.githubmvi.di

import com.example.githubmvi.BuildConfig
import com.example.githubmvi.data.source.Endpoints.BASE_URL
import com.example.githubmvi.data.source.UserService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton




@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val MAX_TIMEOUT_MILLIS = 60_000L

    @Singleton
    @Provides
    fun provideMoshiConverter(): MoshiConverterFactory {
        val moshi: Moshi =  Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        return MoshiConverterFactory.create(moshi).asLenient()
    }

    @Singleton
    @Provides
    fun provideHttpClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .connectTimeout(MAX_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .readTimeout(MAX_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)
            .writeTimeout(MAX_TIMEOUT_MILLIS, TimeUnit.MILLISECONDS)

        if(BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
            builder.addInterceptor(loggingInterceptor)
        }

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        client: OkHttpClient,
        converter: MoshiConverterFactory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(converter)
            .build()
    }

    @Singleton
    @Provides
    fun provideUserService(retrofit: Retrofit): UserService {
        return retrofit.create(UserService::class.java)
    }

}