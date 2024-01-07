package com.example.algarweatherapp.ui

import android.util.TypedValue
import android.widget.TextView
import androidx.databinding.BindingAdapter

@BindingAdapter("textSizeAdapter")
fun TextView.setTextSizeAdapter(textSize: Float) {
//    this.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize)
    this.textSize = textSize
}