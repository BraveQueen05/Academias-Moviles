package pe.flavia.optionaltwo.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.flavia.optionaltwo.databinding.ItemVegetableBinding
import pe.flavia.optionaltwo.entities.Vegetable

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/28/21 - 1:48 AM
    Solera Mobile
*/

private typealias StoreObj = (vegetable: Vegetable) -> Unit

class ListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Any> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onStoreCardClickAction: StoreObj? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return StoreViewHolder(ItemVegetableBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ListAdapter.StoreViewHolder).bind(this.list[position] as Vegetable)
    }

    override fun getItemCount(): Int = this.list.size

    inner class StoreViewHolder(private val dataBinding: ItemVegetableBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(vegetable: Vegetable) {
            dataBinding.vegetable = vegetable
            dataBinding.cv.setOnClickListener {
                this@ListAdapter.onStoreCardClickAction?.let { it(vegetable) }
            }
        }
    }
}