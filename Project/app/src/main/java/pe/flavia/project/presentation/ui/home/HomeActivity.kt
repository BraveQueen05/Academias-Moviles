package pe.flavia.project.presentation.ui.home

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import pe.flavia.project.R
import pe.flavia.project.databinding.ActivityHomeBinding
import pe.flavia.project.di.Injector
import pe.flavia.project.domain.usecases.MoviesUseCase
import pe.flavia.project.presentation.utils.Constants
import pe.flavia.project.presentation.ui.detail.DetailBottomSheet
import pe.flavia.project.presentation.model.ResultModel
import pe.flavia.project.presentation.utils.gone

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    private val moviesViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(MoviesUseCase(Injector.provideAuthorizationRepository()))
    }

    private val adapter by lazy { MovieAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        callViewModelMethods()
        observers()
        setUpRecycler()
    }

    private fun bind(){
        this.binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        this.binding.lifecycleOwner = this
    }

    private fun callViewModelMethods(){
        this.moviesViewModel.getMovies()
    }

    private fun observers(){
        this.moviesViewModel.movies.observe(this, {
            this.binding.apply {
                this.movie = it
                this.progressBar.gone
                this.progressView.gone
            }
            this.adapter.list = it.results
        })
    }

    private fun setUpRecycler(){
        this.binding.rvMovies.adapter = this.adapter
        this.adapter.onClick {
            val bundle = bundleOf(Constants.MOVIE to ResultModel.toResultModel(it))
            DetailBottomSheet().apply {
                this.arguments = bundle
            }.show(supportFragmentManager, DetailBottomSheet::class.simpleName)
        }
    }
}