package com.example.lab_2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class Adapter2(val pos:Int):
    RecyclerView.Adapter<Adapter2.MyViewHolder>(){

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val text: TextView = itemView.findViewById(R.id.textView2)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item2, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val fact: MutableList<MutableList<String>> = mutableListOf()
        for (i in 0..20){
            fact.add(mutableListOf())
            for (j in 0..20)
                fact[i].add("факт нормер $j об аниеме $i")
        }
        holder.text.text= fact[pos][position]

    }

    override fun getItemCount(): Int {
        return 20
    }
}