package com.example.tsd068.newsslice;

import com.example.tsd068.Constants
import com.example.tsd068.model.Result
import io.reactivex.Observable;
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET;
import retrofit2.http.Query;

interface ApiService {

    @GET("top-headlines")
    fun search(@Query("country")query: String = "ca",
               @Query("pagesize")page: Int = 3,
               @Query("apiKey")apikey: String = Constants.newsApiKey ): Observable<Result>

    /**
     * Companion object to create the ApiService
     */
    companion object Factory {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl("https://newsapi.org/v2/")
                    .build()

            return retrofit.create(ApiService::class.java);
        }
    }
}
