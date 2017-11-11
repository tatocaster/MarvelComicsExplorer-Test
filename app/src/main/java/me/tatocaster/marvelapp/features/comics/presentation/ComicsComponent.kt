package me.tatocaster.marvelapp.features.comics.presentation

import dagger.Component
import me.tatocaster.marvelapp.AppComponent
import me.tatocaster.marvelapp.di.scope.ActivityScope
import me.tatocaster.marvelapp.features.comics.usecase.ComicsUseCaseModule

@ActivityScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ComicsModule::class, ComicsUseCaseModule::class))
interface ComicsComponent {
    fun inject(view: ComicsActivity)

}
