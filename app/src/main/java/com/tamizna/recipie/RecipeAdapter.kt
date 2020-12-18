package com.tamizna.recipie

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.tamizna.recipie.databinding.ItemRecipeBinding

class RecipeAdapter(private val onItemClick: (Recipe) -> Unit) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    private val listOfRecipe: MutableList<Recipe> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemRecipeBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.binding.txtTitleRecipe.text = listOfRecipe[position].title
        holder.binding.imgRecipe.setImageResource(listOfRecipe[position].image)

        if (listOfRecipe[position].isLike) {
            holder.binding.imgLike.setColorFilter(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.pink
                )
            )
        } else {
            holder.binding.imgLike.clearColorFilter()
        }

        if (listOfRecipe[position].isBookmark) {
            holder.binding.imgBookmark.setColorFilter(
                ContextCompat.getColor(
                    holder.binding.root.context,
                    R.color.purple_500
                )
            )
        } else {
            holder.binding.imgBookmark.clearColorFilter()
        }

        holder.binding.imgRecipe.setOnClickListener {
            onItemClick(listOfRecipe[position])
        }

        holder.binding.imgLike.setOnClickListener {
            setLiked(position)
        }

        holder.binding.imgBookmark.setOnClickListener {
            setBookmaerked(position)
        }

        holder.binding.imgShare.setOnClickListener {
            holder.binding.root.context.sendData(listOfRecipe[position])
        }
    }

    override fun getItemCount(): Int = listOfRecipe.size

    fun submitData(data: List<Recipe>) {
        listOfRecipe.clear()
        listOfRecipe.addAll(data)
        notifyDataSetChanged()
    }

    private fun setLiked(position: Int) {
        val current = listOfRecipe[position]
        listOfRecipe[position] = current.copy(isLike = ! listOfRecipe[position].isLike)
        notifyItemChanged(position)
    }

    private fun setBookmaerked(position: Int) {
        val current = listOfRecipe[position]
        listOfRecipe[position] = current.copy(isBookmark = !current.isBookmark)
        notifyItemChanged(position)
    }
}

private fun Context.sendData(recipe: Recipe) {
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, recipe.title)
        type = "text/plain"
    }

    val shareIntent = Intent.createChooser(sendIntent, null)
    startActivity(shareIntent)
}
