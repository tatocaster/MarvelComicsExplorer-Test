package me.tatocaster.marvelapp.features.comics.presentation

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
import me.tatocaster.marvelapp.data.api.response.ComicsResponse
import me.tatocaster.marvelapp.features.comics.presentation.layout.ComicItem
import me.tatocaster.marvelapp.features.comics.presentation.layout.ComicItemFullScreen
import me.tatocaster.marvelapp.features.comics.presentation.layout.ComicLayout
import javax.inject.Inject

class ComicsActivity : AppCompatActivity(), ComicsContract.View {

    @Inject
    lateinit var presenter: ComicsContract.Presenter

    private lateinit var scopeGraph: ComicsComponent
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
                ComicLayout.create(mComponentContext)
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

    override fun onLoadComicsList(comics: ComicsResponse) {
        for ((index, value) in comics.data.results.withIndex()) {
            mRecyclerBinder.insertItemAt(index, ComicItem.create(mComponentContext)
                    .comic(value)
                    .listener {
                        mRootView.setComponentAsync(
                                VerticalScroll.create(mComponentContext).childComponent(
                                        ComicItemFullScreen
                                                .create(mComponentContext)
                                                .comic(value)
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
        scopeGraph = DaggerComicsComponent.builder()
                .appComponent(appComponent)
                .comicsModule(ComicsModule(this))
                .build()
        scopeGraph.inject(this)
    }

    companion object {
        private val TAG = "ComicsActivity"
    }
}
