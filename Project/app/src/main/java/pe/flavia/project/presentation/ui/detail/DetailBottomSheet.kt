package pe.flavia.project.presentation.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import pe.flavia.project.R
import pe.flavia.project.databinding.ActivityDetailBinding
import pe.flavia.project.domain.model.Results
import pe.flavia.project.presentation.utils.Constants
import pe.flavia.project.presentation.model.ResultModel
import pe.flavia.project.presentation.utils.gone
import pe.flavia.project.presentation.utils.setImgByURLAndLoading
import pe.flavia.project.presentation.utils.visible

class DetailBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: ActivityDetailBinding

    private var movie = Results()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = DataBindingUtil.inflate(inflater, R.layout.activity_detail, container, false)
        this.binding.lifecycleOwner = this
        getArgs()
        return this.binding.root
    }

    private fun getArgs(){
        this.arguments?.let {
            this.movie = ResultModel.toResult(it.getParcelable(Constants.MOVIE) ?: ResultModel())
            this.binding.movie = this.movie
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpView()
    }

    private fun setUpView(){
        this.binding.img.setImgByURLAndLoading(this.movie.poster_path){
            this@DetailBottomSheet.binding.progressBar.gone
            this@DetailBottomSheet.binding.tvTitle.visible
            this@DetailBottomSheet.binding.tvDescription.visible
        }
    }
}