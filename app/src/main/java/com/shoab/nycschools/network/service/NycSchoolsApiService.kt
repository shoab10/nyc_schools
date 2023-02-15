package com.shoab.nycschools.network.service

import com.shoab.nycschools.model.NycSchool
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.Retrofit
import javax.inject.Inject

class NycSchoolsApiService @Inject constructor(retrofit: Retrofit) {

    private val apiServiceInterface by lazy {
        retrofit.create(NycSchoolApiServiceInterface::class.java)
    }

    suspend fun getSchools(): Flow<List<NycSchool>> {
        return flow { emit(apiServiceInterface.getSchools()) }
    }
}