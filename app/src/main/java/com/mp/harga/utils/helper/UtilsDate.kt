package com.mp.harga.utils.helper

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

@SuppressLint("NewApi")
fun convertDate(parDate: String): String {
    val inPattern = "yyyy-MM-dd'T'HH:mm:ssXXX"
    val outPattern = "dd MMMM yyyy"
    var result = ""

    val inFormat = SimpleDateFormat(inPattern, Locale.getDefault())
    val outFormat = SimpleDateFormat(outPattern, Locale.getDefault())

    try {
        val inDate = inFormat.parse(parDate)
        result = outFormat.format(inDate!!)
    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return result
}