package com.shoab.nycschools.network.service

import com.shoab.nycschools.model.NycSchool
import com.shoab.nycschools.model.SatData
import retrofit2.http.GET
import retrofit2.http.Query

interface NycSchoolApiServiceInterface {

    @GET("s3k6-pzi2")
    suspend fun getSchools(): List<NycSchool>

    @GET("f9bf-2cp4")
    suspend fun getSatDataForSchool(@Query("dbn") dbn: String): SatData
}