package com.shoab.nycschools.model

import com.google.gson.annotations.SerializedName

data class NycSchool(
    @SerializedName("dbn") val dbn: String,
    @SerializedName("school_name") val schoolName: String
)
