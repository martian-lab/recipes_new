package com.martianlab.recipes.entities

sealed class Destination() {
    object MainPage : Destination()
    object RecipeDetails : Destination()
    object Splash : Destination()
}