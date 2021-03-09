package pe.flavia.fragments.list.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.flavia.fragments.databinding.ItemStoreBinding
import pe.flavia.fragments.databinding.ItemUserBinding
import pe.flavia.fragments.entities.RowTypes
import pe.flavia.fragments.entities.User
import pe.flavia.fragments.entities.Vegetable
import pe.flavia.fragments.utils.StoreObj
import pe.flavia.fragments.utils.UserObj

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/7/21 - 8:52 PM
    Solera Mobile
*/

class ListAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: MutableList<Any> = mutableListOf()
        set(value) {
            field = value
            if (this.list.isNotEmpty()) getItemViewType(0)
            notifyDataSetChanged()
        }

    var onStoreCardClickAction: StoreObj? = null
    var onUserCardClickAction: UserObj? = null

    var selectedItemIndex = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            RowTypes.STORE.ordinal -> StoreViewHolder(ItemStoreBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            RowTypes.USER.ordinal -> UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            else -> UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)) //could be an empty custom row
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(getItemViewType(position)){
            RowTypes.STORE.ordinal -> (holder as ListAdapter.StoreViewHolder).bind(this.list[position] as Vegetable)
            RowTypes.USER.ordinal -> (holder as ListAdapter.UserViewHolder).bind(this.list[position] as User)
            else -> (holder as ListAdapter.UserViewHolder).bind(this.list[position] as User)
        }
    }

    override fun getItemCount(): Int = this.list.size

    override fun getItemViewType(position: Int): Int =
        if (this.list[position] is Vegetable) RowTypes.STORE.ordinal else RowTypes.USER.ordinal

    inner class StoreViewHolder(private val dataBinding: ItemStoreBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(vegetable: Vegetable) {
            dataBinding.vegetable = vegetable
            dataBinding.cv.setOnClickListener {
                if (!vegetable.isSelected){
                    val list = this@ListAdapter.list as MutableList<Vegetable>
                    list.forEach { it.isSelected = it == vegetable }
                    if (selectedItemIndex != -1) {
                        list[selectedItemIndex].isSelected = false
                    }
                    selectedItemIndex = adapterPosition
                }
                this@ListAdapter.onStoreCardClickAction?.let { it(vegetable) }
                notifyDataSetChanged()
            }
        }
    }

    inner class UserViewHolder(private val dataBinding: ItemUserBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(user: User) {
            dataBinding.user = user
            dataBinding.cv.setOnClickListener {
                this@ListAdapter.onUserCardClickAction?.let { it(user) }
            }
            dataBinding.cv.setOnClickListener {
                if (!user.isSelected){
                    val list = this@ListAdapter.list as MutableList<User>
                    list.forEach { it.isSelected = it == user }
                    if (selectedItemIndex != -1) {
                        list[selectedItemIndex].isSelected = false
                    }
                    selectedItemIndex = adapterPosition
                }
                this@ListAdapter.onUserCardClickAction?.let { it(user) }
                notifyDataSetChanged()
            }
        }
    }
}