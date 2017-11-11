package me.tatocaster.marvelapp.features.heroes.usecase

import io.reactivex.Flowable
import me.tatocaster.marvelapp.data.api.ApiService
import me.tatocaster.marvelapp.data.api.response.HeroesResponse
import javax.inject.Inject

interface HeroesListRepository {
    fun call(): Flowable<HeroesResponse>
}

class HeroesListRepositoryImpl @Inject constructor(private val mApiService: ApiService) : HeroesListRepository {
    override fun call(): Flowable<HeroesResponse> = mApiService.getHeros()
}