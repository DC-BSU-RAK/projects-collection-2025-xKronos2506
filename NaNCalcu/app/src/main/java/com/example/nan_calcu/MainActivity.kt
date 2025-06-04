package com.example.nan_calcu

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var resultText: TextView

    private val flavors = listOf(
        Pair("Vanilla", R.drawable.vanilla),
        Pair("Chocolate", R.drawable.chocolate),
        Pair("Strawberry", R.drawable.strawberry),
        Pair("Mango", R.drawable.mango),
        Pair("Mint", R.drawable.mint)
    )

    private val toppings = listOf(
        Pair("Sprinkles", R.drawable.sprinkles),
        Pair("Choco Chips", R.drawable.chocochips),
        Pair("Nuts", R.drawable.nuts)
    )

    private var selectedFlavor: Pair<String, Int>? = null
    private var selectedTopping: Pair<String, Int>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        resultText = findViewById(R.id.textResult)

        // Setup flavor buttons
        findViewById<ImageButton>(R.id.flavorVanilla).setOnClickListener {
            selectedFlavor = flavors[0]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.flavorChocolate).setOnClickListener {
            selectedFlavor = flavors[1]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.flavorStrawberry).setOnClickListener {
            selectedFlavor = flavors[2]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.flavorMango).setOnClickListener {
            selectedFlavor = flavors[3]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.flavorMint).setOnClickListener {
            selectedFlavor = flavors[4]
            updateOutput()
        }

        // Setup topping buttons
        findViewById<ImageButton>(R.id.toppingSprinkles).setOnClickListener {
            selectedTopping = toppings[0]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.toppingChocoChips).setOnClickListener {
            selectedTopping = toppings[1]
            updateOutput()
        }
        findViewById<ImageButton>(R.id.toppingNuts).setOnClickListener {
            selectedTopping = toppings[2]
            updateOutput()
        }

        // Randomize combo button — randomize AND immediately show popup
        findViewById<Button>(R.id.randomizeButton).setOnClickListener {
            randomizeCombo()
            updateOutput()
            showComboPopup()
        }

        // Show combo button
        findViewById<Button>(R.id.showComboButton).setOnClickListener {
            showComboPopup()
        }
    }

    private fun randomizeCombo() {
        selectedFlavor = flavors.random()
        selectedTopping = toppings.random()
    }

    private fun updateOutput() {
        resultText.text = when {
            selectedFlavor != null && selectedTopping != null -> "${selectedFlavor!!.first} with ${selectedTopping!!.first}"
            selectedFlavor != null -> selectedFlavor!!.first
            selectedTopping != null -> selectedTopping!!.first
            else -> "Choose your combo"
        }
    }

    private fun showComboPopup() {
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_combo, null)

        val flavorImageView = dialogView.findViewById<ImageView>(R.id.flavorImage)
        val toppingImageView = dialogView.findViewById<ImageView>(R.id.toppingImage)
        val flavorLabel = dialogView.findViewById<TextView>(R.id.flavorLabel)
        val toppingLabel = dialogView.findViewById<TextView>(R.id.toppingLabel)

        if (selectedFlavor != null) {
            flavorImageView.setImageResource(selectedFlavor!!.second)
            flavorLabel.text = selectedFlavor!!.first
        } else {
            flavorLabel.text = "No flavor selected"
        }

        if (selectedTopping != null) {
            toppingImageView.setImageResource(selectedTopping!!.second)
            toppingLabel.text = selectedTopping!!.first
        } else {
            toppingLabel.text = "No topping selected"
        }

        AlertDialog.Builder(this)
            .setView(dialogView)
            .setPositiveButton("Close", null)
            .show()
    }
}
