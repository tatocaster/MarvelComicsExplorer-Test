package me.tatocaster.marvelapp.features.heroes.usecase

import dagger.Module
import dagger.Provides

@Module
class HeroesUseCaseModule {
    @Provides
    fun provideGetAllAvailableProviders(usecase: HeroesListRepositoryImpl): HeroesListRepository = usecase
}