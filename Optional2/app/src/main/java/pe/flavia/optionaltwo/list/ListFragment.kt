package pe.flavia.optionaltwo.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import pe.flavia.optionaltwo.R
import pe.flavia.optionaltwo.databinding.FragmentDetailBinding
import pe.flavia.optionaltwo.databinding.FragmentListBinding
import pe.flavia.optionaltwo.entities.EVegetables

class ListFragment : Fragment() {

    private lateinit var binding: FragmentListBinding

    private val adapter by lazy { ListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.binding = FragmentListBinding.inflate(layoutInflater)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        onClickEvents()
    }

    private fun setUpRecycler(){
        this.adapter.list = EVegetables.getVegetablesList().toMutableList()
        this.binding.rv.adapter = this.adapter
    }

    private fun onClickEvents(){
        this.adapter.onStoreCardClickAction = {
            val action = ListFragmentDirections.actionListFragmentToDetailFragment(it)
            findNavController().navigate(action)
        }
    }
}