package com.example.moviez.presentation.commons

import android.os.Build
import androidx.annotation.RequiresApi
import okhttp3.internal.trimSubstring
import java.text.NumberFormat
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
import java.util.Locale

fun toSingleDecimal(data: Double): String{
    val st = data.toString()
    if(st[1] == '.')return st.trimSubstring(0, 3)
    return st.trimSubstring(0, 4)
}

fun formatMoney(number: Long?): String{
    if(number == null)return "--"
    val numberFormat = NumberFormat.getNumberInstance(Locale.US)
    return numberFormat.format(number)
}

@RequiresApi(Build.VERSION_CODES.O)
fun formatDateString(inputDate: String?): String {
    if(inputDate.isNullOrEmpty())return "--"
    val inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val outputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")
    val date = LocalDate.parse(inputDate, inputFormatter)
    return date.format(outputFormatter)
}

fun formatTime(minutes: Int?): String{
    if(minutes == null)return "--"
    val hours = minutes/ 60
    val remainingMinutes = minutes % 60
    if(hours != 0)return "${hours}h ${remainingMinutes}m"
    else return "${remainingMinutes}m"
}

fun getGender(numb: Int): String{
    when(numb){
        1 -> return "Female"
        2 -> return "Male"
        3 -> return "Non-Binary"
    }
    return "Not-Specified"
}

@RequiresApi(Build.VERSION_CODES.O)
fun getAge(dateOfBirth: String): Int {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val birthDate = LocalDate.parse(dateOfBirth, formatter)
    val currentDate = LocalDate.now()
    val age = Period.between(birthDate, currentDate).years

    return age
}