package com.gayuh.xox.utils

import android.app.Activity
import java.util.Locale

fun Activity.updateLocale(language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = resources.configuration
    config.setLocale(locale)
    @Suppress("DEPRECATION")
    resources.updateConfiguration(config, resources.displayMetrics)
    recreate()
}
