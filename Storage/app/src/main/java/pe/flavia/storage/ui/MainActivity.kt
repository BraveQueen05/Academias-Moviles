package pe.flavia.storage.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import pe.flavia.storage.R
import pe.flavia.storage.data.BooksDataSourceRepository
import pe.flavia.storage.data.BooksDatabase
import pe.flavia.storage.databinding.ActivityMainBinding
import pe.flavia.storage.entities.Book

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val adapter by lazy { BooksAdapter() }

    private lateinit var booksRepository: BooksDataSourceRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initRepository()
        setUpRecycler()
        onClickEvents()
    }

    private fun bind() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun initRepository() {
        this.booksRepository = BooksDataSourceRepository(BooksDatabase(this))
    }

    private fun setUpRecycler() {
        this.binding.rv.adapter = this.adapter
        getListAndShow()
    }

    private fun getListAndShow(){
        val booksList: List<Book> = booksRepository.getAllBooks()
        this.adapter.list = booksList.toMutableList()
    }

    private fun onClickEvents() {
        this.binding.btnAdd.setOnClickListener {
            showDialog()
        }

        this.adapter.onBookRowClickAction = { book ->
            val bundle = bundleOf(BooksConstants.BOOK to book)
            showDialog(bundle)
        }

        this.adapter.deleteBtnClickAction = {book ->
            this.booksRepository.deleteBook(book)
        }
    }

    private fun showDialog(bundle: Bundle?=null){
        DialogFragment().apply {
            bundle?.let { this.arguments = it }
            this.onDismiss = { getListAndShow() }
        }.show(supportFragmentManager, DialogFragment::class.simpleName)
    }
}