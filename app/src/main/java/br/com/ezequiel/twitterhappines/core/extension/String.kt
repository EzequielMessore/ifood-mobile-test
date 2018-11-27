package br.com.ezequiel.twitterhappines.core.extension

import java.text.SimpleDateFormat
import java.util.*

private const val pattern = "EEE MMM dd HH:mm:ss zzz yyyy"

fun String.toDate(): Date = SimpleDateFormat(pattern, Locale.US).parse(this)
