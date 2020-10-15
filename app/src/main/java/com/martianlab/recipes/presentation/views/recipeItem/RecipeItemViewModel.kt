package com.martianlab.recipes.presentation.views.recipeItem

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.views.base.ViewModelForView


class RecipeItemViewModel internal constructor() : ViewModelForView {

    private lateinit var item : Recipe

    val title = ObservableField<String>()
    val isChecked = ObservableBoolean()


    fun bindData(data: Recipe){
        item = data
    }

}
