package me.tatocaster.marvelapp.features.heroes.presentation

import me.tatocaster.marvelapp.data.api.response.HeroesResponse

interface HeroesContract {

    interface View {
        fun onLoadHeroesList(heros: HeroesResponse)

        fun showMessage(s: String)
    }

    interface Presenter {
        fun onCreate()

        fun onDestroy()
    }
}
