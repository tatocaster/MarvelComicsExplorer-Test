package me.tatocaster.marvelapp.features.heroes.presentation

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatocaster.marvelapp.features.heroes.usecase.HeroesListRepository
import javax.inject.Inject


class HeroesPresenter @Inject constructor(
        private val view: HeroesContract.View,
        private val repository: HeroesListRepository) : HeroesContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate() {
        disposables.add(repository.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { heros ->
                            view.onLoadHeroesList(heros)
                        },
                        { e ->
                            Log.e("error", e.message, e)
                            view.showMessage("Failed to load content")
                        }
                ))
    }

    override fun onDestroy() {
        disposables.clear()
    }

}