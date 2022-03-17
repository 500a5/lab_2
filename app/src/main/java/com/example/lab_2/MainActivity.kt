package com.example.lab_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.text.Html
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    val URL = "https://anime-facts-rest-api.herokuapp.com/api/v1"
    var okHttpClient: OkHttpClient = OkHttpClient()
    lateinit var progressBar: ProgressBar
    val listAnime: MutableList<Anime> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
                for (i in 1..12) {
                    listAnime.add(
                        Anime(
                            (JSONObject(json).getJSONArray("data").getJSONObject(i)
                                .get("anime_name")).toString(),
                            (JSONObject(json).getJSONArray("data").getJSONObject(i)
                                .get("anime_img")).toString()
                        )
                    )
                }

                runOnUiThread {
                    progressBar.visibility = View.GONE
                    recyclerView.adapter = Adapter1(listAnime)


                }
            }
        })

    }

    data class Anime(
        val name: String,
        val img: String
    )

}