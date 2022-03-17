package com.example.lab_2

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity2 : AppCompatActivity() {
    var URL = "https://anime-facts-rest-api.herokuapp.com/api/v1/"
    var okHttpClient: OkHttpClient = OkHttpClient()
    lateinit var progressBar: ProgressBar
    val listAnimeFact: MutableList<String> = mutableListOf()
    lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val name = intent.getStringExtra("name")
        URL += name
        progressBar = findViewById(R.id.progressBar)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        load()

    }

    private fun load() {

        runOnUiThread {
            progressBar.visibility = View.VISIBLE
        }

        val request: Request = Request.Builder().url(URL).build()

        okHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call?, e: IOException?) {
            }

            override fun onResponse(call: Call?, response: Response?) {
                val json = response?.body()?.string()
                val i = JSONObject(json).get("total_facts").toString().toInt()
                for (j in 1..i - 1) {
                    listAnimeFact.add(
                        ("факт$j:  " + JSONObject(json).getJSONArray("data").getJSONObject(j)
                            .get("fact")).toString()

                    )
                }

                runOnUiThread {
                    progressBar.visibility = View.GONE
                    recyclerView.adapter = Adapter2(listAnimeFact)


                }
            }
        })

    }
}