package com.shoab.nycschools.repository

import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.network.service.NycSchoolsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NycSchoolsRepository @Inject constructor(
    private val nycSchoolsApiService: NycSchoolsApiService
) {
    suspend fun getNycSchools(): Flow<List<NycSchool>> {
        return nycSchoolsApiService.getSchools()
    }
}