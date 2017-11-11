package me.tatocaster.marvelapp.data.api

import io.reactivex.Flowable
import me.tatocaster.marvelapp.data.api.response.HeroesResponse
import retrofit2.http.GET

interface ApiService {
    @GET("v1/public/characters")
    fun getHeros(): Flowable<HeroesResponse>
}