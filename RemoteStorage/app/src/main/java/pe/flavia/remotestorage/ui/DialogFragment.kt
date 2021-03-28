package pe.flavia.remotestorage.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.DatabaseReference.*
import com.google.firebase.database.FirebaseDatabase
import pe.flavia.remotestorage.R
import pe.flavia.remotestorage.databinding.FragmentDialogBinding
import pe.flavia.remotestorage.entities.Book

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/25/21 - 2:05 AM
    Solera Mobile
*/

private typealias onDismiss = () -> Unit

class DialogFragment: DialogFragment() {
    private lateinit var binding: FragmentDialogBinding
    private lateinit var reference: DatabaseReference
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
        initFirebaseDb()
        getArgumentsData()
        onClickEvents()
    }

    private fun initFirebaseDb() {
        this.reference = FirebaseDatabase.getInstance().reference
    }

    private fun getArgumentsData(){
        this.arguments?.let {
            this.book = it.getParcelable(BooksConstants.BOOK)
            setData()
        }
    }

    private fun setData(){
        this.book?.let {
            this.binding.etBookName.setText(it.bookName)
            this.binding.etAuthorName.setText(it.bookAuthor)
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
            val noteId = this.reference.child("books").push().key
            val note= Book(noteId,bookName,authorName)
            noteId?.let {
                this.reference.child("books").child(it).setValue(note, object: CompletionListener{
                    override fun onComplete(databaseError: DatabaseError?, databaseReference: DatabaseReference) {
                        databaseError?.let {dbError ->
                            Toast.makeText(requireContext(), dbError.message, Toast.LENGTH_SHORT).show()
                        }?:run{
                            dismiss()
                        }
                    }
                })
            }
            this.onDismiss?.let { it() }
            dismiss()
        }
    }

    private fun updateBook(){
        if (validator()) {
            this.book?.idBook?.let {
                this.reference.child("books").child(it).updateChildren(Book(it, bookName, authorName).toMap(), object: CompletionListener{
                    override fun onComplete(databaseError: DatabaseError?, databaseReference: DatabaseReference) {
                        databaseError?.let {dbError ->
                            Toast.makeText(requireContext(), dbError.message, Toast.LENGTH_SHORT).show()
                        }?:run{
                            dismiss()
                        }
                    }
                })
            }
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