package com.tamizna.recipie

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.tamizna.recipie.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val padding = resources.getDimensionPixelSize(R.dimen.padding) / 4

        val onItemClick = { recipe: Recipe ->
            val intent = Intent(this, RecipeDetailActivity::class.java)
            recipe.toBundle(intent)
            startActivity(intent)
        }

        binding.rvRecipe.run {
            addItemDecoration(RVItemDecoration(padding))
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = RecipeAdapter(onItemClick).apply {
                submitData(getRecipeData())
            }
        }
    }

    private fun getRecipeData(): List<Recipe> {
        val ingredients = resources.getStringArray(R.array.bahan)
        val steps = resources.getStringArray(R.array.langkah)

        return listOf(
            Recipe(
                "Cinnamon Roll",
                R.drawable.cinnamon_roll,
                ingredients[0],
                steps[0],
                false,
                false
            ),
            Recipe(
                "Strawberry Cheese Cake",
                R.drawable.strawberry_cheesecake,
                ingredients[1],
                steps[1],
                false,
                false
            ),
            Recipe("Banana Bites", R.drawable.banana_bites, ingredients[0], steps[0], false, false),
            Recipe(
                "Blueberry Muffin",
                R.drawable.blueberry_muffin,
                ingredients[1],
                steps[1],
                false,
                false
            ),
            Recipe("Brownies", R.drawable.brownies, ingredients[0], steps[0], false, false),
            Recipe("Garlic Bread", R.drawable.garlic_bread, ingredients[1], steps[1], false, false),
            Recipe("Marble Cake", R.drawable.marble_cake, ingredients[0], steps[0], false, false),
            Recipe("Pancake", R.drawable.pancake, ingredients[1], steps[1], false, false)
        )
    }
}