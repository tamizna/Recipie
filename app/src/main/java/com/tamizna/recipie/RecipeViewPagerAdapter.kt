package com.tamizna.recipie

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class RecipeViewPagerAdapter(fragment: FragmentActivity, val ingredients : String, val steps : String) :
    FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment = when (position) {
        0 -> {
            DetailRecipeFragment(0, ingredients, steps)
        }
        else -> {
            DetailRecipeFragment(1, ingredients, steps)
        }

    }


}