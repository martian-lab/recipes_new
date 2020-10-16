package com.martianlab.recipes.presentation.fragments.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martianlab.recipes.databinding.FragmentRecipeDetailsBinding
import com.martianlab.recipes.presentation.views.ingredient.IngredientItemView
import com.martianlab.recipes.presentation.views.recipeCategory.RecipeCategoryItemView
import com.martianlab.recipes.presentation.views.stage.StageItemView

class DetailsFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProvider(this)[DetailsViewModel::class.java]
    }

    lateinit var binding: FragmentRecipeDetailsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        FragmentRecipeDetailsBinding.inflate(inflater)
            .apply { viewModel = this@DetailsFragment.viewModel }
            .apply { binding = this }
            .root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.recipeId = arguments?.getLong("recipeId")

        binding.ingredientsListView.itemViewClass = IngredientItemView::class
        binding.stagesListView.itemViewClass = StageItemView::class

    }


}