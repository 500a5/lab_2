package com.example.lab_2

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class Adapter1(val list: List<MainActivity.Anime>) :
    RecyclerView.Adapter<Adapter1.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.findViewById(R.id.textView)
        val img: ImageView = itemView.findViewById(R.id.imageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item1, parent, false)
        val context = parent.context
        val holder = MyViewHolder(itemView)
        itemView.setOnClickListener {
            val intent = Intent(context, MainActivity2::class.java)
            intent.putExtra("name", holder.title.text)
            context.startActivity(intent)
        }
        return holder
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.title.text = list[position].name
        Picasso.get().load(list[position].img).into(holder.img)

        //holder.img.setImageResource(list.second[position])

    }

    override fun getItemCount(): Int {
        return list.size
    }
}