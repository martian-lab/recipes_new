package com.martianlab.recipes.presentation.views.recipeCategory

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.views.base.ViewModelForView


class RecipeCategoryItemViewModel internal constructor() : ViewModelForView {

    private lateinit var item : Category

    val title = ObservableField<String>()
    val isChecked = ObservableBoolean()


    fun bindData(data: Category){
        item = data
    }

}
