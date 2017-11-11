package me.tatocaster.marvelapp.data

import me.tatocaster.marvelapp.data.api.ApiService

interface DataComponent {
    fun exposeApiService(): ApiService
}
