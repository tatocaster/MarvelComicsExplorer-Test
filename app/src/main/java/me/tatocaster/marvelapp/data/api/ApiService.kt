package me.tatocaster.marvelapp.data.api

import io.reactivex.Flowable
import me.tatocaster.marvelapp.data.api.response.ComicsResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/characters/1009610/comics")
    fun getHeros(): Flowable<ComicsResponse>
}