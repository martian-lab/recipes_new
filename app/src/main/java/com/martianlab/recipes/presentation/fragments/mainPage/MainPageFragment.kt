package com.martianlab.recipes.presentation.fragments.mainPage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.martianlab.recipes.R
import com.martianlab.recipes.databinding.FragmentRecipesBinding
import com.martianlab.recipes.presentation.views.base.BaseRecyclerView
import com.martianlab.recipes.presentation.views.recipeCategory.RecipeCategoryItemView

class MainPageFragment : Fragment() {

    val viewModel by lazy {
        ViewModelProvider(this)[MainPageViewModel::class.java]
    }

//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
//                              savedInstanceState: Bundle?): View? {
//
//        return inflater.inflate(R.layout.fragment_recipes, container, false)
//    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        FragmentRecipesBinding.inflate(inflater)
            .apply { viewModel = this@MainPageFragment.viewModel }
            .root

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//        //(activity as BottomTabsActivity).hideBottomPanel()
//        //viewModel.doSmth()
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        activity?.findViewById<BaseRecyclerView>(R.id.recyclerView)?.itemViewClass = RecipeCategoryItemView::class
    }


}