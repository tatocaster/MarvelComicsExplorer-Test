package me.tatocaster.marvelapp.features.heroes.presentation

import dagger.Component
import me.tatocaster.marvelapp.AppComponent
import me.tatocaster.marvelapp.di.scope.ActivityScope
import me.tatocaster.marvelapp.features.heroes.usecase.HeroesUseCaseModule

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(HeroesModule::class, HeroesUseCaseModule::class))
interface HeroesComponent {
    fun inject(view: HeroesActivity)

}
