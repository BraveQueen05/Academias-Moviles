package pe.flavia.optional1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText
import pe.flavia.optional1.databinding.ActivityFormBinding
import pe.flavia.optional1.databinding.ActivityMainBinding
import pe.flavia.optional1.form.*

class FormActivity : AppCompatActivity(), IValidator, View.OnFocusChangeListener {

    private lateinit var binding: ActivityFormBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind()
        registerValidations()
        onClickEvents()
    }

    private fun bind(){
        this.binding = ActivityFormBinding.inflate(layoutInflater)
        setContentView(this.binding.root)

        this.binding.etName.onFocusChangeListener = this
        this.binding.etEmail.onFocusChangeListener = this
        this.binding.etPassword.onFocusChangeListener = this
    }

    override var validations: MutableList<Validator> = mutableListOf()

    private fun registerValidations(){
        this.validations.register(this.binding.etName, getString(R.string.error_1_at_least), minLength = 1)
        this.validations.register(this.binding.etName, getString(R.string.error_only_letters), typeFormat = CSRestrictionRule.ALPHABETIC)
        this.validations.register(this.binding.etEmail, getString(R.string.error_3_at_least), minLength = 3)
        this.validations.register(this.binding.etEmail, getString(R.string.error_bad_format), typeFormat = CSRestrictionRule.EMAIL)
        this.validations.register(this.binding.etPassword, getString(R.string.error_1_at_least), minLength = 1)
    }

    private fun onClickEvents(){
        this.binding.btnSignup.setOnClickListener {
            if(this.validations.showAllErrors) Toast.makeText(this, getString(R.string.success), Toast.LENGTH_SHORT).show()
            else Toast.makeText(this, getString(R.string.error_bad_form), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        if (!hasFocus && v is TextInputEditText) this.validations.showErrorMsgToEditText(v)
    }
}