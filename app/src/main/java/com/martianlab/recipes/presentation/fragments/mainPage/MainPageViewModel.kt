package com.martianlab.recipes.presentation.fragments.mainPage

import androidx.databinding.ObservableBoolean
import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.martianlab.recipes.App
import com.martianlab.recipes.domain.RecipesInteractor
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Destination
import com.martianlab.recipes.entities.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainPageViewModel(

) : ViewModel(), CoroutineScope by CoroutineScope(Dispatchers.IO) {

    @Inject
    lateinit var interactor: RecipesInteractor

    val categoryList = ObservableField<List<Category>>()
    val isLoading = ObservableBoolean(true)

    init {
        App.component.inject(this)
        launch {
            categoryList.set( interactor.getCategories() )
            isLoading.set(false)
        }
    }

}