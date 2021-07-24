package com.majesticreader.presentation.util

import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.pm.PackageInfoCompat
import androidx.core.os.ConfigurationCompat
import java.util.*

fun Context.getCurrentLocale(): Locale = ConfigurationCompat.getLocales(
    this.resources.configuration
)[0]

fun Context.getVersionCode(): Long {
    return try {
        PackageInfoCompat.getLongVersionCode(
            this.packageManager.getPackageInfo(this.packageName, 0)
        )
    } catch (e: PackageManager.NameNotFoundException) {
        LogUtils.e("${e.message}")
        0
    }
}

fun Context.getVersionName(): String {
    var version = ""
    try {
        val pInfo = this.packageManager.getPackageInfo(this.packageName, 0)
        version = pInfo.versionName
    } catch (e: PackageManager.NameNotFoundException) {
        LogUtils.e("${e.message}")
    }
    return version
}
