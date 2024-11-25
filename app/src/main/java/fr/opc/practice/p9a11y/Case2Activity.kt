package fr.opc.practice.p9a11y

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
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
        }

        binding.addRecipeToBasket.setOnClickListener {
            Toast.makeText(this, getString(R.string.recette_ajout_au_panier), Toast.LENGTH_SHORT)
                .show()
        }

        binding.recipeCard.setOnClickListener {
            // TODO navigate to recipe screen
        }

        binding.favouriteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
        binding.addRecipeToBasket.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO

        binding.recipeCard.setOnClickListener {
            binding.favouriteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
            binding.addRecipeToBasket.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
            // TODO navigate to recipe screen
        }



    }

    private fun setFavouriteButtonIcon(isFavourite: Boolean) {
        if (isFavourite) {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_on)
            binding.favouriteButton.contentDescription = getString(R.string.remove_from_favourites) // "Retirer des favoris"
        } else {
            binding.favouriteButton.setImageResource(R.drawable.ic_favourite_off)
            binding.favouriteButton.contentDescription = getString(R.string.add_to_favourites) // "Ajouter aux favoris"
        }
    }

}
