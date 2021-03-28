package pe.flavia.optional1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pe.flavia.optional1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        onClickEvents()
    }

    private fun bind(){
        this.binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
    }

    private fun onClickEvents(){
        this.binding.btnDropbox.setOnClickListener {
            startActivity(Intent(this, DropboxActivity::class.java))
        }

        this.binding.btnFlipboard.setOnClickListener {
            startActivity(Intent(this, FlipboardActivity::class.java))
        }

        this.binding.btnForm.setOnClickListener {
            startActivity(Intent(this, FormActivity::class.java))
        }
    }
}