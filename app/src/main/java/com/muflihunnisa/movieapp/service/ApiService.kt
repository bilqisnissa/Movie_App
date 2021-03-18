package com.muflihunnisa.movieapp.service

import com.muflihunnisa.movieapp.model.ResponseMovie
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("discover")
    fun getMovieData(
        @Query("apiKey") apiKey: String?,
        @Query("&language") lang : String?
    ): Call<ResponseMovie>
}