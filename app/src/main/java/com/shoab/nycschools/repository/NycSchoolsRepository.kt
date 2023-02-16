package com.shoab.nycschools.repository

import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.model.SatData
import com.shoab.nycschools.network.service.NycSchoolsApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface NycSchoolsRepository {
    suspend fun getNycSchools(): Flow<List<NycSchool>>
    suspend fun getStatData(idn: String): Flow<SatData>
}

class NycSchoolsRepositoryImpl @Inject constructor(
    private val nycSchoolsApiService: NycSchoolsApiService
): NycSchoolsRepository  {
    override suspend fun getNycSchools(): Flow<List<NycSchool>> {
        return nycSchoolsApiService.getSchools()
    }

    override suspend fun getStatData(idn: String): Flow<SatData> {
        return nycSchoolsApiService.getSatData(idn)
    }
}