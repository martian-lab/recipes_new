package com.martianlab.recipes.routing

import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import com.martianlab.recipes.R
import com.martianlab.recipes.domain.api.RoutingApi
import com.martianlab.recipes.entities.Destination
import com.martianlab.recipes.presentation.fragments.mainPage.MainPageFragment
import com.martianlab.recipes.presentation.fragments.splash.SplashFragment
import java.lang.ref.WeakReference
import javax.inject.Inject


class RouterImpl @Inject constructor(
    val activity: WeakReference<FragmentActivity>?,
    val fragmentManager: FragmentManager?
) : RoutingApi {

    override fun goTo(destination: Destination) {

        //activity?.get()?.let {
        fragmentManager?.let {
            when (destination) {
                is Destination.Splash ->
                    it.beginTransaction().replace(R.id.container, SplashFragment()).commit()
                is Destination.MainPage ->
                    it.beginTransaction().replace(R.id.container, MainPageFragment()).commit()
                Destination.RecipeDetails -> TODO()
            }
        }

    }
}