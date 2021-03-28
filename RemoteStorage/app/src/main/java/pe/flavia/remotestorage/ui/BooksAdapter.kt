package pe.flavia.remotestorage.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.firebase.ui.database.FirebaseRecyclerAdapter
import com.firebase.ui.database.FirebaseRecyclerOptions
import pe.flavia.remotestorage.databinding.ItemBookBinding
import pe.flavia.remotestorage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/25/21 - 1:14 AM
    Solera Mobile
*/

typealias BookObj = (book: Book) -> Unit

class BooksAdapter(options: FirebaseRecyclerOptions<Book>) : FirebaseRecyclerAdapter<Book, BooksAdapter.BooksViewHolder>(options) {

    var onBookRowClickAction: BookObj? = null
    var deleteBtnClickAction: BookObj? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BooksAdapter.BooksViewHolder =
        BooksViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: BooksViewHolder, position: Int, model: Book) {
        holder.bind(model)
    }

    inner class BooksViewHolder(private val dataBinding: ItemBookBinding) : RecyclerView.ViewHolder(dataBinding.root){
        fun bind(book: Book) {
            dataBinding.book = book
            dataBinding.ctrBook.setOnClickListener {
                this@BooksAdapter.onBookRowClickAction?.let { it(book) }
            }
            dataBinding.btnDelete.setOnClickListener {
                this@BooksAdapter.deleteBtnClickAction?.let {
                    it(book)
                }
            }
        }
    }
}