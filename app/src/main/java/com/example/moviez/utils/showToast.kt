package com.example.moviez.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message: String, duration: Int) {
    Toast.makeText(context, message, duration).show()
}