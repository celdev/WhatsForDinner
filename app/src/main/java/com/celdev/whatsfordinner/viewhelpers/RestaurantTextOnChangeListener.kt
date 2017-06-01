package com.celdev.whatsfordinner

import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView


class RestaurantTextOnChangeListener(val whiteText : TextView, val blackText : TextView) : TextWatcher{


    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(text: CharSequence?, p1: Int, p2: Int, p3: Int) {
        whiteText.text = text
        blackText.text = text
    }
}