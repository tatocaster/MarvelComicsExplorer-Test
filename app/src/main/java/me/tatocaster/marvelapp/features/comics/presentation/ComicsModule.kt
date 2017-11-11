package me.tatocaster.marvelapp.features.comics.presentation

import dagger.Module
import dagger.Provides

@Module
class ComicsModule(private val view: ComicsContract.View) {

    @Provides
    fun provideView(): ComicsContract.View = this.view

    @Provides
    fun providePresenter(presenter: ComicsPresenter): ComicsContract.Presenter = presenter
}
