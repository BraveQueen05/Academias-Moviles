package pe.flavia.project.data.remote.api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import pe.flavia.project.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/4/21 - 4:45 PM
    Solera Mobile
*/

class ApiModule {

    private lateinit var apiInterface: ApiInterface

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())

    fun build(): ApiInterface {
        val httpClient: OkHttpClient.Builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) httpClient.addInterceptor(interceptor())

        val retrofit: Retrofit = builder.client(httpClient.build()).build()
        apiInterface = retrofit.create(
            ApiInterface::class.java
        )

        return apiInterface
    }

    private fun interceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }
}