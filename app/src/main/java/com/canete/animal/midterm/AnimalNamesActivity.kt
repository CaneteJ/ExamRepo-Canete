package com.canete.animal.midterm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

@Suppress("DEPRECATION")
class AnimalNamesActivity : AppCompatActivity() {


    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<nameList>
    private lateinit var imageId : Array<Int>
    private lateinit var heading : Array<String>
    lateinit var animalDesc : Array<String>

    companion object {

        const val REQUEST_DETAILS = 101 // Choose any unique request code
        lateinit var allAnimals : Array<String>
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animal_names)

        imageId = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.a,
            R.drawable.b,


        )

        heading = arrayOf(
            "Ant",
            "Bear",
            "Cat",
            "Dog",
            "Elephant",
            "Fox",
            "Giraffe",
            "Horse",
            "Iguana",
            "Jaguar",
            "Kangaroo",
            "Lion",
            "Monkey",
            "Newt",
            "Octopus",
            "Penguin",
            "Quokka",
            "Rabbit",
            "Snake",
            "Tiger",
            "Uakari",
            "Vulture",
            "Wolf",
            "Xenopus",
            "Yak",
            "Zebra"
        )
        animalDesc = arrayOf(

            getString(R.string.desc_a),
            getString(R.string.desc_b),
            getString(R.string.desc_c),
            getString(R.string.desc_d),
            getString(R.string.desc_e),
            getString(R.string.desc_f),
            getString(R.string.desc_g),
            getString(R.string.desc_h),
            getString(R.string.desc_i),
            getString(R.string.desc_j),
            getString(R.string.desc_k),
            getString(R.string.desc_l),
            getString(R.string.desc_m),
            getString(R.string.desc_n),
            getString(R.string.desc_o),
            getString(R.string.desc_p),
            getString(R.string.desc_q),
            getString(R.string.desc_r),
            getString(R.string.desc_s),
            getString(R.string.desc_t),
            getString(R.string.desc_u),
            getString(R.string.desc_v),
            getString(R.string.desc_w),
            getString(R.string.desc_x),
            getString(R.string.desc_y),
            getString(R.string.desc_z),

        )


        newRecyclerView = findViewById(R.id.recyclerView)
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)

        newArrayList = arrayListOf()
        getuserdata ()


    }


    private fun getuserdata() {
        for (i in imageId.indices) {

            val name = nameList(imageId[i], heading[i])
            newArrayList.add(name)

        }
        val adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.setClickLister(object : MyAdapter.onClickListner {
            override fun onClick(position: Int) {

                val intent = Intent(this@AnimalNamesActivity, AnimalDetailsActivity::class.java)
                intent.putExtra("heading", newArrayList[position].heading)
                intent.putExtra("imageId", newArrayList[position].title_image)
                intent.putExtra("animalDesc", animalDesc[position])
                intent.putExtra("isBlocked", newArrayList[position].isBlocked)
                startActivityForResult(intent, REQUEST_DETAILS)

            }


        })

    }
    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_DETAILS) {
            if (resultCode == RESULT_OK) {
                val position = data?.getIntExtra("position", -1)
                val isBlocked = data?.getBooleanExtra("isBlocked", false)

                if (position != -1) {
                    if (isBlocked == true) {
                        // Remove the blocked animal from the list
                        position?.let { newArrayList.removeAt(it) }
                        position?.let { newRecyclerView.adapter?.notifyItemRemoved(it) }
                    } else {
                        // Handle the case where you might unblock an animal
                    }
                }
            }
        }
    }

    fun onManageButtonClick(view: View) {}
}

