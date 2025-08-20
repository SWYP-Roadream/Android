package com.yeogijeogi.data.di

import com.yeogijeogi.data.repository.LoginRepositoryImpl
import com.yeogijeogi.domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun bindsLoginRepository(repositoryImpl: LoginRepositoryImpl): LoginRepository
}