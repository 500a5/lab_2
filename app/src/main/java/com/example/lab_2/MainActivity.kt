package com.example.lab_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Adapter1(initList())
    }
    fun initList(): Pair<MutableList<String>, MutableList<Int>> {
        val title = mutableListOf<String>()
        val img = mutableListOf<Int>()
        for (i in 0..20){
            title.add("аниме $i ")
            if (i%2==0)
                img.add(R.drawable.q1)
            else
                img.add(R.drawable.q2)
        }
        return Pair(title, img)

    }

}