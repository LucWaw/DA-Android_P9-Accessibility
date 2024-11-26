package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import fr.opc.practice.p9a11y.databinding.ActivityCase2Binding



class Case2Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        var isFavourite = false
        setFavouriteButtonIcon(isFavourite)
        binding.favouriteButton.setOnClickListener {
            isFavourite = !isFavourite
            setFavouriteButtonIcon(isFavourite)
            updateAccessibilityAction(isFavourite) // Mettre à jour l'accessibilité

        }

        binding.addRecipeToBasket.setOnClickListener {
            addToBasket()
        }



        binding.favouriteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        binding.addRecipeToBasket.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO

        binding.recipeCard.setOnClickListener {
            // TODO navigate to recipe screen
        }

        ViewCompat.addAccessibilityAction(
            binding.recipeCard,
            getString(R.string.add_to_basket)
        ) { _, _ ->
            addToBasket()
            true
        }

        updateAccessibilityAction(isFavourite)
    }

    private fun addToBasket() {
        Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
            .show()
    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
        }
    }
    private var accessibilityActionId: Int = View.NO_ID

    fun updateAccessibilityAction(isFavourite: Boolean) {
        // Supprime l'action précédente si elle existe
        if (accessibilityActionId != View.NO_ID) {
            ViewCompat.removeAccessibilityAction(binding.recipeCard, accessibilityActionId)
        }

        // Ajoute une nouvelle action avec un label adapté
        accessibilityActionId = ViewCompat.addAccessibilityAction(
            binding.recipeCard,
            if (isFavourite) getString(R.string.remove_from_favourites) else getString(R.string.add_to_favourites)
        ) { _, _ ->
            binding.favouriteButton.performClick()
            true
        }
    }

}
