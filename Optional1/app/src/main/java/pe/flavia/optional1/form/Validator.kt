package pe.flavia.optional1.form

import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.textfield.TextInputEditText

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/28/21 - 12:28 AM
    Solera Mobile
*/

enum class CSRestrictionRule {
    ALPHABETIC,
    EMAIL,
    TEXT
}

class Validator(
    var editText                : TextInputEditText,
    var errorMessage            : String,
    var minLength               : Int = 0,
    var typeFormat              : CSRestrictionRule = CSRestrictionRule.TEXT
){
    var isAValidFormat          = false

    internal fun initObserver(){
        this.editText.doAfterTextChanged {
            restrictions()
        }
    }

    internal fun restrictions(){
        val text = this.editText.text.toString()
        this.isAValidFormat = false
        if (text.length >= this.minLength) this.regexMatcherValidator(text)
    }

    private fun regexMatcherValidator(text: String){
        this.isAValidFormat = when (this.typeFormat) {
            CSRestrictionRule.TEXT                         -> true
            CSRestrictionRule.ALPHABETIC                   -> text.isAValidAlphabeticText
            CSRestrictionRule.EMAIL                        -> text.isAValidEmail
        }
    }
}

fun MutableList<Validator>.register(editText     : TextInputEditText,
                                      errorMessage : String,
                                      minLength    : Int = 0,
                                      typeFormat   : CSRestrictionRule = CSRestrictionRule.TEXT){
    val validation = Validator(editText, errorMessage, minLength, typeFormat)
    validation.initObserver()
    this.add(validation)
}

fun MutableList<Validator>.showErrorMsgToEditText(editText: TextInputEditText){
    this.forEach { it.restrictions() }
    this.firstOrNull {
        editText==it.editText && !it.isAValidFormat
    }?.let {validation ->
        validation.editText.error = validation.errorMessage
    }
}

val MutableList<Validator>.allValidationsAreCorrect:Boolean
    get(){
        return this.none { !it.isAValidFormat && it.editText.visibility != View.GONE }
    }

val MutableList<Validator>.showAllErrors: Boolean
    get() {
        this.forEach { this.showErrorMsgToEditText(it.editText) }
        return this.allValidationsAreCorrect
    }

interface IValidator{
    var validations: MutableList<Validator>
}