package com.canete.animal.midterm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyAdapter (private val animalNames : ArrayList<nameList>) : RecyclerView.Adapter<MyAdapter.MyViewHolder> () {

    private lateinit var nListener : onClickListner

    interface onClickListner{

        fun onClick(position: Int)

    }

    fun setClickLister(listener: onClickListner){

        nListener = listener

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.name_layout, parent, false)
        return MyViewHolder(itemView,nListener)
    }

    override fun getItemCount(): Int {

        return animalNames.size

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = animalNames[position]
        holder.titleImage.setImageResource(currentItem.title_image)
        holder.tvHeading.text = currentItem.heading

    }
    class MyViewHolder(itemView: View, listner: onClickListner) : RecyclerView.ViewHolder(itemView){


        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val tvHeading : TextView = itemView.findViewById(R.id.tvHeading)

        init{

            itemView.setOnClickListener {

                listner.onClick(adapterPosition)

            }

        }

    }
}