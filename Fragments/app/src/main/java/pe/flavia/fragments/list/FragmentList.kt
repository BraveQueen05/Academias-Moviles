package pe.flavia.fragments.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import pe.flavia.fragments.R
import pe.flavia.fragments.databinding.FragmentListBinding
import pe.flavia.fragments.entities.EUser
import pe.flavia.fragments.entities.EVegetables
import pe.flavia.fragments.list.adapter.ListAdapter

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/7/21 - 8:01 PM
    Solera Mobile
*/

class FragmentList: Fragment() {

    private lateinit var binding: FragmentListBinding

    private val adapter by lazy { ListAdapter() }

    private var listener: IFragmentList? = null
    private var click = false

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is IFragmentList) this.listener = context else throw Exception(this.getString(R.string.error_listener_impl))
    }

    override fun onDetach() {
        super.onDetach()
        this.listener = null
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentListBinding.inflate(layoutInflater)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        onClickEvents()
    }

    private fun setUpRecycler(){
        setUpAdapter()
        this.binding.rv.adapter = this.adapter
    }

    private fun setUpAdapter(){
        this.adapter.apply {
            this.list = EVegetables.getVegetablesList().toMutableList()
            this@FragmentList.listener?.firstItem(this.list.first())
            this.onStoreCardClickAction = {
                this@FragmentList.listener?.showDetail(it)
            }
            this.onUserCardClickAction = {
                this@FragmentList.listener?.showDetail(it)
            }
        }
    }

    private fun onClickEvents(){
        this.binding.btnSwitch.setOnClickListener {
            scrollToTop()
            if (!this.click) setUpSwitch(EUser.getUsersList().toMutableList(), true)
            else setUpSwitch(EVegetables.getVegetablesList().toMutableList(), false)
        }
    }

    private fun setUpSwitch(list: MutableList<Any>, click: Boolean){
        this.adapter.list = list
        this.binding.rv.scheduleLayoutAnimation()
        this@FragmentList.listener?.firstItem(this.adapter.list.first())
        this.click = click
    }

    private fun scrollToTop() {
        this.binding.rv.layoutManager?.scrollToPosition(0)
    }

    interface IFragmentList{
        fun showDetail(item: Any)
        fun firstItem(item: Any)
    }
}