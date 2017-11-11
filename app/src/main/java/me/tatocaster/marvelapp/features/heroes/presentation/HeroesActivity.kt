package me.tatocaster.marvelapp.features.heroes.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import me.tatocaster.marvelapp.App
import me.tatocaster.marvelapp.AppComponent
import me.tatocaster.marvelapp.data.api.response.HeroesResponse
import javax.inject.Inject

class HeroesActivity : AppCompatActivity(), HeroesContract.View {

    @Inject
    lateinit var presenter: HeroesContract.Presenter

    private lateinit var scopeGraph: HeroesComponent


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScopeGraph(App.getAppContext(this).appComponent)

        presenter.onCreate()
    }

    override fun onLoadHeroesList(heros: HeroesResponse) {
    }

    override fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerHeroesComponent.builder()
                .appComponent(appComponent)
                .heroesModule(HeroesModule(this))
                .build()
        scopeGraph.inject(this)
    }

    companion object {
        private val TAG = "HerosActivity"
    }
}
