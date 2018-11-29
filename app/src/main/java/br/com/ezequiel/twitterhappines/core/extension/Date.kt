package br.com.ezequiel.twitterhappines.core.extension

import java.text.SimpleDateFormat
import java.util.*

private const val pattern = "HH:mm a - MMM d, yyyy"

fun Date.toSimpleString(): String {
    val format = SimpleDateFormat(pattern, Locale.getDefault())
    return format.format(this)
}