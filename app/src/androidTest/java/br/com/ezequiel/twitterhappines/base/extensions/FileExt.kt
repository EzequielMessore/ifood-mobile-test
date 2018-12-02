package br.com.ezequiel.twitterhappines.base.extensions

import android.content.Context
import android.support.annotation.RawRes
import com.google.gson.Gson
import org.apache.commons.io.IOUtils


infix fun Context.getJson(@RawRes raw: Int): String {
    val inputStream = resources.openRawResource(raw)
    val json = IOUtils.toString(inputStream)
    IOUtils.closeQuietly(inputStream)
    return json
}

infix fun <T> String.jsonToObject(clazz: Class<T>): T {
    return Gson().fromJson<T>(this, clazz)
}