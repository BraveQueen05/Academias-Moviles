package pe.flavia.optional1.form

import java.util.regex.Pattern

/*
    Created by: Flavia Figueroa
    Email: ffigueroa@solera.pe
    
    3/28/21 - 12:51 AM
    Solera Mobile
*/

val String.isAValidEmail: Boolean
    get() {
        val emailPattern = "^[A-Z0-9a-z._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}\$"
        return Pattern.compile(emailPattern).matcher(this).matches()
    }

val String.isAValidAlphabeticText: Boolean
    get() {
        val emailPattern = "^[a-zA-ZáéíóúÁÉÍÓÚ ñ'-]*\$"
        return Pattern.compile(emailPattern).matcher(this).matches()
    }