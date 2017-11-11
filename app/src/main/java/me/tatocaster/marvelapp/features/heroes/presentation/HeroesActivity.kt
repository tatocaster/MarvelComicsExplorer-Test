package me.tatocaster.marvelapp.features.heroes.presentation

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.facebook.litho.ComponentContext
import com.facebook.litho.LithoView
import com.facebook.litho.widget.Recycler
import com.facebook.litho.widget.RecyclerBinder
import com.facebook.litho.widget.VerticalScroll
import me.tatocaster.marvelapp.App
import me.tatocaster.marvelapp.AppComponent
import me.tatocaster.marvelapp.data.api.response.HeroesResponse
import me.tatocaster.marvelapp.features.heroes.presentation.layout.HeroItem
import me.tatocaster.marvelapp.features.heroes.presentation.layout.HeroItemFullScreen
import me.tatocaster.marvelapp.features.heroes.presentation.layout.HerosLayout
import javax.inject.Inject

class HeroesActivity : AppCompatActivity(), HeroesContract.View {

    @Inject
    lateinit var presenter: HeroesContract.Presenter

    private lateinit var scopeGraph: HeroesComponent
    private lateinit var mComponentContext: ComponentContext
    private lateinit var mRecyclerBinder: RecyclerBinder
    private var mSingleView: Boolean = true
    private lateinit var mRootView: LithoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupScopeGraph(App.getAppContext(this).appComponent)
        initViews()
        mRootView = LithoView.create(
                this,
                HerosLayout.create(mComponentContext)
                        .binder(mRecyclerBinder)
                        .build()
        )

        setContentView(mRootView)
        presenter.onCreate()
    }

    private fun initViews() {
        mComponentContext = ComponentContext(this)
        mRecyclerBinder = RecyclerBinder.Builder().build(mComponentContext)
    }

    override fun onLoadHeroesList(heros: HeroesResponse) {
        for ((index, value) in heros.data.results.withIndex()) {
            mRecyclerBinder.insertItemAt(index, HeroItem.create(mComponentContext)
                    .hero(value)
                    .listener {
                        mRootView.setComponentAsync(
                                VerticalScroll.create(mComponentContext).childComponent(
                                        HeroItemFullScreen
                                                .create(mComponentContext)
                                                .hero(value)
                                )
                                        .build())
                        mSingleView = true
                    }
                    .build())
        }
    }

    override fun showMessage(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }

    override fun onBackPressed() {
        if (mSingleView) {
            mSingleView = false
            mRootView.setComponentAsync(Recycler.create(mComponentContext).binder(mRecyclerBinder).build())
            return
        }
        super.onBackPressed()
    }

    private fun setupScopeGraph(appComponent: AppComponent) {
        scopeGraph = DaggerHeroesComponent.builder()
                .appComponent(appComponent)
                .heroesModule(HeroesModule(this))
                .build()
        scopeGraph.inject(this)
    }

    companion object {
        private val TAG = "HeroesActivity"
    }
}
