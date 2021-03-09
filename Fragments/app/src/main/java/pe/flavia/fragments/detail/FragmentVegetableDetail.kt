package pe.flavia.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.flavia.fragments.databinding.FragmentVegetableDetailBinding
import pe.flavia.fragments.entities.Vegetable
import pe.flavia.fragments.utils.VEGETABLE
import pe.flavia.fragments.utils.scrollToTop

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/7/21 - 8:01 PM
    Solera Mobile
*/

class FragmentVegetableDetail: Fragment() {

    private lateinit var binding: FragmentVegetableDetailBinding

    private var vegetable = Vegetable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentVegetableDetailBinding.inflate(layoutInflater)
        this.binding.lifecycleOwner = this@FragmentVegetableDetail
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData(){
        this.arguments?.let {
            this.vegetable = it.getParcelable(VEGETABLE) ?: Vegetable()
            this.binding.vegetable = this@FragmentVegetableDetail.vegetable
        }
    }

    fun updateData(vegetable: Vegetable){
        this.binding.vegetable = vegetable
        this.binding.nested.scrollToTop
    }
}