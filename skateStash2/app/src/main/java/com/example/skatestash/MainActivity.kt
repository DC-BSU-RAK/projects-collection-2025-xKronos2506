package com.example.skatestash

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(), ProductAdapter.OnProductClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        val products = listOf(
            Product("Street Blazer", 89.99, R.drawable.baker),
            Product("Vert Viper", 99.99, R.drawable.santacruz),
            Product("Cruise King", 109.99, R.drawable.antihero),
            Product("Park Phantom", 119.99, R.drawable.heroin)
        )

        recyclerView.layoutManager = GridLayoutManager(this, 3) // 3 columns for 3 rows
        recyclerView.adapter = ProductAdapter(products, this)
    }

    override fun onProductClick(product: Product) {
        AlertDialog.Builder(this)
            .setTitle("Add to Cart")
            .setMessage("Add ${product.name} to cart for \$${product.price}?")
            .setPositiveButton("Confirm Order") { _, _ ->
                Toast.makeText(this, "Order processed, thank you.", Toast.LENGTH_LONG).show()
            }
            .setNegativeButton("Cancel", null)
            .show()
    }
}
