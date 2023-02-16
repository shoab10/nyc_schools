package com.shoab.nycschools.inject

import com.shoab.nycschools.repository.NycSchoolsRepository
import com.shoab.nycschools.repository.NycSchoolsRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Singleton
    @Binds
    fun bindsMyModelRepository(
        repository: NycSchoolsRepositoryImpl
    ): NycSchoolsRepository
}