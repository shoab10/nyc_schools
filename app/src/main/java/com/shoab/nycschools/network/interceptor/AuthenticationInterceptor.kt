package com.shoab.nycschools.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthenticationInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        // Todo : Add auth key here
        val request = chain.request().newBuilder()
            .addHeader("Auth", "key")
            .build()
        return chain.proceed(request)
    }
}