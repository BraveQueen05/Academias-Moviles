package pe.flavia.fragments.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import pe.flavia.fragments.databinding.FragmentUserDetailBinding
import pe.flavia.fragments.entities.User
import pe.flavia.fragments.utils.USER
import pe.flavia.fragments.utils.scrollToTop

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/8/21 - 2:54 AM
    Solera Mobile
*/

class FragmentUserDetail: Fragment() {
    private lateinit var binding: FragmentUserDetailBinding

    private var user = User()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentUserDetailBinding.inflate(layoutInflater)
        this.binding.lifecycleOwner = this@FragmentUserDetail
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getData()
    }

    private fun getData(){
        this.arguments?.let {
            this.user = it.getParcelable(USER) ?: User()
            this.binding.user = this@FragmentUserDetail.user
        }
    }

    fun updateData(user: User){
        this.binding.user = user
        this.binding.nested.scrollToTop
    }
}