package com.martianlab.data.repository

import com.martianlab.recipes.entities.Theme
import javax.inject.Inject

internal class ThemeMapper @Inject constructor() {

    fun Theme.toPreference() = name

    fun String.toThemeEntity() = Theme.valueOf(this)
}