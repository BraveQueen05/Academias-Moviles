package pe.flavia.storage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pe.flavia.storage.databinding.ItemBookBinding
import pe.flavia.storage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/25/21 - 1:14 AM
    Solera Mobile
*/

typealias BookObj = (book: Book) -> Unit

class BooksAdapter: RecyclerView.Adapter<BooksAdapter.BooksViewHolder>() {

    var list: MutableList<Book> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var onBookRowClickAction: BookObj? = null
    var deleteBtnClickAction: BookObj? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.BooksViewHolder =
        BooksViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BooksAdapter.BooksViewHolder, position: Int) {
        holder.bind(this.list[position])
    }

    override fun getItemCount(): Int = this.list.size

    inner class BooksViewHolder(private val dataBinding: ItemBookBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(book: Book) {
            dataBinding.book = book
            dataBinding.ctrBook.setOnClickListener {
                this@BooksAdapter.onBookRowClickAction?.let { it(book) }
            }
            dataBinding.btnDelete.setOnClickListener {
                this@BooksAdapter.deleteBtnClickAction?.let {
                    it(book)
                    this@BooksAdapter.list.remove(book)
                    this@BooksAdapter.notifyDataSetChanged()
                }
            }
        }
    }
}