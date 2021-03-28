package pe.flavia.remotestorage.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import com.firebase.ui.database.FirebaseRecyclerOptions
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.Query
import pe.flavia.remotestorage.databinding.ActivityMainBinding
import pe.flavia.remotestorage.entities.Book

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var adapter: BooksAdapter ?= null

    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        initFirebaseDb()
        getListAndShow()
        setUpRecycler()
        onClickEvents()
    }

    private fun bind() {
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun initFirebaseDb() {
        this.reference = FirebaseDatabase.getInstance().reference
    }

    private fun setUpRecycler() {
        this.binding.rv.adapter = this.adapter
    }

    private fun getListAndShow(){
        val query: Query = this.reference.child("books")
        val options= FirebaseRecyclerOptions.Builder<Book>()
            .setQuery(query, Book::class.java)
            .build()
        this.adapter = BooksAdapter(options)
    }

    private fun onClickEvents() {
        this.binding.btnAdd.setOnClickListener {
            showDialog()
        }

        this.adapter?.onBookRowClickAction = { book ->
            val bundle = bundleOf(BooksConstants.BOOK to book)
            showDialog(bundle)
        }

        this.adapter?.deleteBtnClickAction = {book ->
            book.idBook?.let {
                this.reference.child("books").child(it).removeValue { databaseError, _ ->
                    databaseError?.let { dbError ->
                        Toast.makeText(this@MainActivity, dbError.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }
    }

    private fun showDialog(bundle: Bundle?=null){
        DialogFragment().apply {
            bundle?.let { this.arguments = it }
            this.onDismiss = { getListAndShow() }
        }.show(supportFragmentManager, DialogFragment::class.simpleName)
    }

    override fun onStart() {
        super.onStart()
        this.adapter?.startListening()
    }

    public override fun onStop() {
        super.onStop()
        this.adapter?.stopListening()
    }
}