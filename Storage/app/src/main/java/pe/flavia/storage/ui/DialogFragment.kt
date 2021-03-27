package pe.flavia.storage.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import pe.flavia.storage.R
import pe.flavia.storage.data.BooksDataSourceRepository
import pe.flavia.storage.data.BooksDatabase
import pe.flavia.storage.databinding.FragmentDialogBinding
import pe.flavia.storage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/25/21 - 2:05 AM
    Solera Mobile
*/

private typealias onDismiss = () -> Unit

class DialogFragment: DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private lateinit var booksRepository: BooksDataSourceRepository
    private var book: Book ?= null

    var onDismiss: onDismiss ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NO_TITLE, R.style.DialogFragmentStyle)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        this.binding = FragmentDialogBinding.inflate(inflater)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRepository()
        getArgumentsData()
        onClickEvents()
    }

    private fun initRepository() {
        this.booksRepository = BooksDataSourceRepository(BooksDatabase(requireContext()))
    }

    private fun getArgumentsData(){
        this.arguments?.let {
            this.book = it.getParcelable(BooksConstants.BOOK)
            setData()
        }
    }

    private fun setData(){
        this.book?.let {
            this.binding.etBookName.setText(it.name)
            this.binding.etAuthorName.setText(it.author)
        }
    }

    private fun onClickEvents(){
        this.binding.btnSend.setOnClickListener {
            this.book?.let { updateBook() } ?: kotlin.run { saveBook() }
        }
    }

    private val bookName: String
        get() = this.binding.etBookName.text.toString()

    private val authorName: String
        get() = this.binding.etAuthorName.text.toString()

    private fun saveBook(){
        if (validator()) {
            this.booksRepository.addBook(Book(name = bookName, author =  authorName))
            this.onDismiss?.let { it() }
            dismiss()
        }
    }

    private fun updateBook(){
        if (validator()) {
            this.book?.let {
                this.booksRepository.updateBook(Book(it.id, this.bookName, this.authorName))
            } ?: return
            dismiss()
        }
    }

    private fun validator(): Boolean{
        if (bookName.isEmpty()) this.binding.etBookName.error = getString(R.string.error_enter_field)
        if (authorName.isEmpty()) this.binding.etAuthorName.error = getString(R.string.error_enter_field)

        return bookName.isNotEmpty() && authorName.isNotEmpty()
    }

    override fun onDismiss(dialog: DialogInterface) {
        this.onDismiss?.let { it() }
        super.onDismiss(dialog)
    }
}