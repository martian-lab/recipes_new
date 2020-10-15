package com.martianlab.recipes.presentation.views.recipeCategory

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import com.martianlab.recipes.databinding.RecipeCategoryItemBinding
import com.martianlab.recipes.databinding.RecipeItemBinding
import com.martianlab.recipes.entities.Category
import com.martianlab.recipes.entities.Recipe
import com.martianlab.recipes.presentation.views.base.RecyclerItemView


class RecipeCategoryItemView @JvmOverloads constructor(
        context: Context,
        attrs: AttributeSet? = null,
        defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle), RecyclerItemView<Category> {

    override val viewModel = RecipeCategoryItemViewModel()

    override val binding: RecipeCategoryItemBinding = RecipeCategoryItemBinding.inflate(activity.layoutInflater, this, true)
            .apply { viewModel = this@RecipeCategoryItemView.viewModel }

    override fun bindData(data: Category) {
        viewModel.bindData(data)
    }


}

