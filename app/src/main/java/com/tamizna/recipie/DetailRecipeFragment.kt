package com.tamizna.recipie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.tamizna.recipie.databinding.FragmentDetailRecipeBinding

class DetailRecipeFragment(val position: Int, val ingredient: String, val steps: String) :
    Fragment() {

    private var binding: FragmentDetailRecipeBinding? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailRecipeBinding.inflate(inflater, container, false)
        return binding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        when (position) {
            0 -> {
                binding!!.txtLabelDescRecipe.text = "Bahan"
                binding!!.txtDescRecipe.text = ingredient
            }
            else -> {
                binding!!.txtLabelDescRecipe.text = "Langkah-langkah"
                binding!!.txtDescRecipe.text = steps
            }
        }
    }
}