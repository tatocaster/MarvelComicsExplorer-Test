package me.tatocaster.marvelapp.features.comics.presentation

import android.util.Log
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import me.tatocaster.marvelapp.features.comics.usecase.ComicsListRepository
import javax.inject.Inject


class ComicsPresenter @Inject constructor(
        private val view: ComicsContract.View,
        private val repository: ComicsListRepository) : ComicsContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()

    override fun onCreate() {
        disposables.add(repository.call()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { heros ->
                            view.onLoadComicsList(heros)
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