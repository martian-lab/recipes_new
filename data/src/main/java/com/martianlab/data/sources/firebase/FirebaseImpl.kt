package com.martianlab.data.sources.firebase

import android.app.Application
import android.util.Log
import com.google.firebase.FirebaseApp
import com.google.firebase.analytics.FirebaseAnalytics
import com.martianlab.recipes.domain.api.FirebaseApi
import com.martianlab.recipes.entities.FirebaseEvent
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class FirebaseImpl @Inject constructor(application: Application) : FirebaseApi {

    private val analytics = FirebaseAnalytics.getInstance(application)

    init {
        FirebaseApp.initializeApp(application)
    }

    override fun logEvent(event: FirebaseEvent) {
        Log.d("Analytics", "Firebase event: name=${event.name}, params=${event.params}")
        analytics.logEvent(event.name, event.params)
    }
}