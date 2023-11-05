package com.canete.animal.midterm

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView



class ManageBlockActivity : AppCompatActivity() {
    private var isBlocked: Boolean = false
    private lateinit var recyclerView: RecyclerView
    private lateinit var blockedAnimalsList: ArrayList<nameList>

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_block)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)

        blockedAnimalsList = ArrayList()

        loadBlockedAnimals()

        val adapter = MyAdapter(blockedAnimalsList)
        recyclerView.adapter = adapter

        adapter.setClickLister(object : MyAdapter.onClickListner {
            override fun onClick(position: Int) {
                // Handle unblocking an animal here
                unblockAnimal(position)
            }
        })
    }

    private fun loadBlockedAnimals() {
        blockedAnimalsList.clear()

        // Filter the 'allAnimals' list to get the blocked animals
        val blockedAnimals = AnimalNamesActivity.allAnimals.filter { it.isEmpty()}

        blockedAnimalsList.addAll(blockedAnimalsList)
    }

    private fun unblockAnimal(position: Int) {
        // Update the 'isBlocked' flag for the selected animal
        blockedAnimalsList[position].isBlocked = false

        // Notify the adapter of the change
        recyclerView.adapter?.notifyItemChanged(position)
    }
}