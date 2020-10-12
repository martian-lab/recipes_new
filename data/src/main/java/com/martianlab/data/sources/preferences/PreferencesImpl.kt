package com.martianlab.data.sources.preferences

import android.app.Application
import android.content.Context
import com.martianlab.recipes.domain.api.PreferencesApi
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

private const val RECIPE_PREFERENCES = "recipe_prefs"
private const val KEY_isFirstAppLaunch = "isFirstAppLaunch"
private const val KEY_deviceId = "device_id"
private const val KEY_numberOfAppLaunches = "launch_number"
private const val KEY_application_started = "application_started"
private const val KEY_appTheme = "storeTheme"


@Singleton
internal class PreferencesImpl @Inject constructor(
    application: Application,
    private val defaultAppTheme: String
) : PreferencesApi {

    private val preferences = application.getSharedPreferences(
        RECIPE_PREFERENCES,
        Context.MODE_PRIVATE
    )

    override val isFirstAppLaunch: Boolean
        get() = with(preferences) {
            getBoolean(KEY_isFirstAppLaunch, true)
                .also { edit().putBoolean(KEY_isFirstAppLaunch, false).apply() }
        }

    override val isApplicationStarted: Boolean
        get() = with(preferences) {
            getBoolean(KEY_application_started, false)
                .also { edit().putBoolean(KEY_application_started, true).apply() }
        }

    override val deviceId: String
        get() = with(preferences) {
            getString(KEY_deviceId, null)
                ?: "A-${UUID.randomUUID()}".also { edit().putString(KEY_deviceId, it).apply() }
        }

    override var numberOfAppLaunches: Int
        get() = preferences.getInt(KEY_numberOfAppLaunches, 0)
        set(value) {
            preferences.edit()
                .putInt(KEY_numberOfAppLaunches, value)
                .apply()
        }

    override var appTheme: String
        get() = preferences.getString(KEY_appTheme, defaultAppTheme)!!
        set(value) {
            preferences.edit().putString(KEY_appTheme, value).apply()
        }

}