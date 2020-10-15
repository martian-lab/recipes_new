package com.martianlab.recipes.presentation.fragments.splash


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.martianlab.recipes.App
import com.martianlab.recipes.R
import com.martianlab.recipes.domain.api.RoutingApi
import com.martianlab.recipes.entities.Destination
import javax.inject.Inject


class SplashFragment : Fragment() {


    @Inject
    lateinit var routingApi: RoutingApi

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        App.component.inject(this)
        //(activity as BottomTabsActivity).hideBottomPanel()
        routingApi.goTo(Destination.MainPage)
    }
}
