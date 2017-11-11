package me.tatocaster.marvelapp.features.comics.usecase

import io.reactivex.Flowable
import me.tatocaster.marvelapp.data.api.ApiService
import me.tatocaster.marvelapp.data.api.response.ComicsResponse
import javax.inject.Inject

interface ComicsListRepository {
    fun call(): Flowable<ComicsResponse>
}

class ComicsListRepositoryImpl @Inject constructor(private val mApiService: ApiService) : ComicsListRepository {
    override fun call(): Flowable<ComicsResponse> = mApiService.getHeros()
}