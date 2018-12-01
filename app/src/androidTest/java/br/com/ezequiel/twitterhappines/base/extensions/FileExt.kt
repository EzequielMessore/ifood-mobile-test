package br.com.ezequiel.twitterhappines.base.extensions

import android.content.Context
import android.support.annotation.RawRes
import org.apache.commons.io.IOUtils


infix fun Context.getJson(@RawRes raw: Int): String {
    val inputStream = resources.openRawResource(raw)
    val json = IOUtils.toString(inputStream)
    IOUtils.closeQuietly(inputStream)
    return json
}