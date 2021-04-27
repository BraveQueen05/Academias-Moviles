package pe.flavia.project.presentation.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import pe.flavia.project.R
import pe.flavia.project.databinding.ItemMovieBinding
import pe.flavia.project.domain.model.Results

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    4/26/21 - 2:20 AM
    Solera Mobile
*/

class MovieAdapter: RecyclerView.Adapter<MovieAdapter.ViewHolder>() {

    var list: List<Results> = listOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    private lateinit var onClick: (item: Results) -> Unit

    fun onClick(action: (item: Results) -> Unit){
        this.onClick = action
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.item_movie, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.list[position])
    }

    override fun getItemCount(): Int = this.list.size

    inner class ViewHolder(private val dataBinding: ViewDataBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(item: Results) {
            dataBinding as ItemMovieBinding
            dataBinding.result = item

            dataBinding.ctrMovie.setOnClickListener {
                this@MovieAdapter.onClick(item)
            }
        }
    }
}