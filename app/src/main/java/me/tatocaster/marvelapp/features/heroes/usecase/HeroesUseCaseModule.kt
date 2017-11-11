package me.tatocaster.marvelapp.features.heroes.usecase

import dagger.Module
import dagger.Provides

/**
 * Created by tatocaster on 29.10.17.
 */
@Module
class HeroesUseCaseModule {
    @Provides
    fun provideGetAllAvailableProviders(usecase: HeroesListRepositoryImpl): HeroesListRepository = usecase
}