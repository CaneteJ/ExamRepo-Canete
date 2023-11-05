package com.canete.animal.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class AnimalDetailsActivity : AppCompatActivity() {
    private var currentPosition: Int = -1
    private var isBlocked: Boolean = false
    private lateinit var blockButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val headerTitle: TextView = findViewById(R.id.heading)
        val mainDesc: TextView = findViewById(R.id.description)
        val imageNews: ImageView = findViewById(R.id.imageView)
        blockButton = findViewById(R.id.blockButton)

        val bundle: Bundle? = intent.extras
        val heading = bundle?.getString("heading")
        val imageId = bundle?.getInt("imageId")
        val animalDesc = bundle?.getString("animalDesc")
        isBlocked = bundle?.getBoolean("isBlocked", false) ?: false
        currentPosition = bundle?.getInt("position", -1) ?: -1

        headerTitle.text = heading
        mainDesc.text = animalDesc
        imageNews.setImageResource(imageId ?: 0)

        blockButton.text = if (isBlocked) "Unblock Animal" else "Block Animal"

        blockButton.setOnClickListener {
            onBlockAnimalButtonClick()
        }
    }

    private fun onBlockAnimalButtonClick() {
        // Toggle the blocked status
        isBlocked = !isBlocked

        // Create an intent to send back the blocked status
        val resultIntent = Intent()
        resultIntent.putExtra("position", currentPosition)
        resultIntent.putExtra("isBlocked", isBlocked)
        setResult(RESULT_OK, resultIntent)
        finish()
    }
}