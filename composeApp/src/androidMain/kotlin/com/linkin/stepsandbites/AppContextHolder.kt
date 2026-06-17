package com.linkin.stepsandbites

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

private val Context.onboardingDataStore: DataStore<Preferences> by preferencesDataStore("onboarding")

object AppContextHolder {
    lateinit var appContext: Context

    val onboardingDataStore: DataStore<Preferences> by lazy {
        appContext.onboardingDataStore
    }
}
