package com.heptogontest.shrihari.view

import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.core.view.iterator
import java.util.regex.Pattern

class UiUtils {
    companion object
    {
        fun isValidEmail(email: String?): Boolean {
            println(email)
            val EMAIL_PATTERN = ("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")
            val pattern = Pattern.compile(EMAIL_PATTERN)
            val matcher = pattern.matcher(email)
            return matcher.matches()
        }
        fun isTextValid(text: String?): Boolean {
            println(text)
            return (text.isNullOrBlank()  && text.isNullOrEmpty())
        }

          }
}