package me.tatocaster.marvelapp.features.heroes.presentation

import dagger.Module
import dagger.Provides

@Module
class HeroesModule(private val view: HeroesContract.View) {

    @Provides
    fun provideView(): HeroesContract.View = this.view

    @Provides
    fun providePresenter(presenter: HeroesPresenter): HeroesContract.Presenter = presenter
}
