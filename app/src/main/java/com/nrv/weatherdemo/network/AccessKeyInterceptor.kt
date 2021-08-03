package com.nrv.weatherdemo.network

import okhttp3.HttpUrl
import okhttp3.Interceptor
import okhttp3.Response


class AccessKeyInterceptor : Interceptor{

    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        val url = request.url.newBuilder().addQueryParameter(ACCESS_KEY, ACCESS_KEY_VALUE).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }

    companion object {
        private const val ACCESS_KEY = "access_key"
        private const val ACCESS_KEY_VALUE = "19571dcbe9c2d9a99cce94ee2fc776ed"
    }
}