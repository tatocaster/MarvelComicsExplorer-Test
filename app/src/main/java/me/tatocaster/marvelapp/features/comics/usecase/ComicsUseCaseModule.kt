package me.tatocaster.marvelapp.features.comics.usecase

import dagger.Module
import dagger.Provides

@Module
class ComicsUseCaseModule {
    @Provides
    fun provideGetAllAvailableProviders(usecase: ComicsListRepositoryImpl): ComicsListRepository = usecase
}