package com.martianlab.data.sources.android_settings

import android.annotation.SuppressLint
import android.app.Application
import android.content.res.Configuration
import android.os.Build
import android.provider.Settings
import android.util.DisplayMetrics
import com.martianlab.recipes.domain.api.AndroidSettingsApi
import com.martianlab.recipes.entities.DeviceType
import com.martianlab.recipes.entities.Theme
import javax.inject.Singleton
import kotlin.math.sqrt

@Singleton
class AndroidSettingsImpl(private val application: Application) : AndroidSettingsApi {

    override val androidId: String
        @SuppressLint("HardwareIds")
        get() = Settings.Secure.getString(application.contentResolver, Settings.Secure.ANDROID_ID)

    override val deviceName: String
        get() {
            fun capitalize(s: String?): String {
                if (s == null || s.isEmpty()) {
                    return ""
                }
                val first = s[0]
                return if (Character.isUpperCase(first)) {
                    s
                } else {
                    "${Character.toUpperCase(first)}${s.substring(1)}"
                }
            }

            val manufacturer = Build.MANUFACTURER
            val model = Build.MODEL

            return if (model.startsWith(manufacturer)) {
                capitalize(model)
            } else "${capitalize(manufacturer)} $model"
        }

    override val androidVersion: String = "Android ${android.os.Build.VERSION.RELEASE} ( Api ${android.os.Build.VERSION.SDK_INT} )"

    private lateinit var cachedAdvertisingId: String



    override val deviceType: DeviceType

    init {
        val displayMetrics = application.resources.displayMetrics
        val yInches = displayMetrics.heightPixels / displayMetrics.ydpi
        val xInches = displayMetrics.widthPixels / displayMetrics.xdpi
        val diagonalInches = sqrt((xInches * xInches + yInches * yInches).toDouble())
        deviceType = if (diagonalInches < 7) DeviceType.SMARTPHONE else DeviceType.TABLET
    }

    override val systemTheme: Theme
        get() = when (application.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
            Configuration.UI_MODE_NIGHT_NO -> Theme.LIGHT// Night mode is not active, we're using the light theme
            Configuration.UI_MODE_NIGHT_YES -> Theme.DARK// Night mode is active, we're using dark theme
            else -> Theme.LIGHT
        }

    override val displayMetrics: DisplayMetrics
        get() = application.resources.displayMetrics
}