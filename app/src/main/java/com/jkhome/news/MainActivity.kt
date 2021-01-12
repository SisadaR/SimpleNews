package com.jkhome.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jkhome.news.databinding.ActivityMainBinding
import java.util.zip.Inflater

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.searchButton.setOnClickListener {
            val url = getUrl()
            val queue = Volley.newRequestQueue(this)
            val stringRequest = StringRequest(
                Request.Method.GET,
                url,
                { response ->
                    response
                },
                { error ->
                    error
                }
                )

            queue.add(stringRequest)
            queue.start()
        }
    }

    private fun getUrl(): String {
        val word = binding.searchEditText.text
        val apiKey = "72daf1c7-cf9f-45e5-98d3-5bb3d6a373a6"
        val pageNumber = 1
        val pageSize = 10
        return "https://content.guardianapis.com/search?q=$word&page=$pageNumber&page-size=$pageSize&api-key=$apiKey"

    }
}