package fr.opc.practice.p9a11y

import android.content.res.ColorStateList
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import fr.opc.practice.p9a11y.databinding.ActivityCase3Binding

class Case3Activity : AppCompatActivity() {
    private lateinit var binding: ActivityCase3Binding

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCase3Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.pseudoEdit.doOnTextChanged { text, _, _, _ ->
            text?.length?.let { textLength ->
                binding.validateButton.isEnabled = textLength > 2
                binding.pseudoEdit.backgroundTintList = if (textLength > 2) {
                    binding.pseudoEdit.error = null
                    ColorStateList.valueOf(resources.getColor(R.color.green400, theme))
                } else {

                    binding.pseudoEdit.error = getString(R.string.pseudo_to_small)
                    ColorStateList.valueOf(resources.getColor(R.color.red400, theme))
                }
            }
        }
        binding.validateButton.isEnabled = false

    }
}
