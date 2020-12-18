package com.tamizna.recipie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.tamizna.recipie.databinding.ActivityRecipeDetailBinding

class RecipeDetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityRecipeDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipe = BundleParam.fromBundle<Recipe>(intent.extras)

        binding.txtTitleRecipe.text = recipe.title
        binding.imgRecipe.setImageResource(recipe.image)
        binding.viewPagerRecipe.adapter = RecipeViewPagerAdapter(this, recipe.ingredients, recipe.steps)

        TabLayoutMediator(binding.tabLayoutRecipe, binding.viewPagerRecipe) { tab, position ->
            when (position) {
                0 -> tab.text = "Ingredients"
                else -> tab.text = "Steps"
            }
        }.attach()
    }
}