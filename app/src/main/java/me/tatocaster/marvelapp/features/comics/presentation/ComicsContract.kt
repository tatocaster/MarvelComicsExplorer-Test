package me.tatocaster.marvelapp.features.comics.presentation

import me.tatocaster.marvelapp.data.api.response.ComicsResponse

interface ComicsContract {

    interface View {
        fun onLoadComicsList(comics: ComicsResponse)

        fun showMessage(s: String)
    }

    interface Presenter {
        fun onCreate()

        fun onDestroy()
    }
}
