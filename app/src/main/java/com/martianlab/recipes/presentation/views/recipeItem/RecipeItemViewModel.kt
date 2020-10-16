package com.martianlab.recipes.presentation.views.recipeItem

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import com.martianlab.recipes.App
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.entities.Destination
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.views.base.ViewModelForView
import javax.inject.Inject


class RecipeItemViewModel internal constructor() : ViewModelForView {

    init{
        App.component.inject(this)
    }

    @Inject
    lateinit var interactor: RecipesInteractor

    private lateinit var item : Recipe

    val title = ObservableField<String>()
    val imageURL = ObservableField<String>()


    fun bindData(data: Recipe){
        item = data
        title.set(item.title)
        imageURL.set(item.imageURL)
    }

    fun onClick(){
        interactor.goTo(Destination.RecipeDetails(item.id))
    }

}
