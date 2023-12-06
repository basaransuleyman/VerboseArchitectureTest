package com.tech.persistence.extension

import android.content.Context

fun Context.getJsonDataFromAsset(fileName: String): String {
    return this.assets.open(fileName).bufferedReader().use { it.readText() }
}