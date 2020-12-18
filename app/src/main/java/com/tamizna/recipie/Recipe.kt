package com.tamizna.recipie

import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class Recipe(
    val title: String,
    @DrawableRes val image: Int,
    val ingredients: String,
    val steps: String,
    val isLike: Boolean,
    val isBookmark: Boolean
) : BundleParam

interface BundleParam : Parcelable {
    companion object {
        const val BUNDLE_PARAM_PREFIX = "bp:"

        inline fun <reified T : BundleParam> fromBundle(bundle: Bundle?): T {
            val key = BUNDLE_PARAM_PREFIX + T::class.java.simpleName
            return bundle?.getParcelable<T>(key) ?: error("No value")
        }
    }

    private val bundleKey get() = BUNDLE_PARAM_PREFIX + this::class.java.simpleName

    fun toBundle(intent: Intent? = null): Bundle {
        return if (intent != null) {
            intent.putExtra(bundleKey, this)
            intent.extras ?: Bundle()
        } else {
            Bundle().apply {
                putParcelable(bundleKey, this)
            }
        }
    }
}
