package com.shoab.nycschools.model

import com.google.gson.annotations.SerializedName

data class SatData(
    @SerializedName("dbn") val dbn : String,
    @SerializedName("school_name") val schoolName: String,
    @SerializedName("num_of_sat_test_takers") val testTakersNumber: String,
    @SerializedName("sat_critical_reading_avg_score") val readingAvg: String,
    @SerializedName("sat_math_avg_score") val mathAvg: String,
    @SerializedName("sat_writing_avg_score") val writingAvg: String
)
