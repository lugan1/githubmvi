package com.example.githubmvi.di

import com.example.githubmvi.data.repository.UserRepository
import com.example.githubmvi.data.repository.impl.UserRepositoryImpl
import com.example.githubmvi.data.source.UserService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun provideUserRepository(
        userService: UserService
    ): UserRepository {
        return UserRepositoryImpl(userService)
    }

}