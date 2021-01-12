package com.jkhome.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.jkhome.news.databinding.ActivityMainBinding
import org.json.JSONObject
import java.lang.Exception
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
                    Toast.makeText(this,response,Toast.LENGTH_SHORT).show()
                    extractJSON(response)
//                    try {
//                        extractJSON(response)
//                    }
//                    catch (e: Exception){
//                        e.printStackTrace()
//                    }
                },
                { error ->
                    Toast.makeText(this,error.message,Toast.LENGTH_SHORT).show()
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

    private fun extractJSON(jsonString: String)
    {
        val jsonObject = JSONObject(jsonString)
        val jsonResponseBody = jsonObject.getJSONObject("response")
        val result = jsonResponseBody.getJSONArray("results")

        val list = mutableListOf<Data>()
        for (i in 0..9)
        {
            val item = result.getJSONObject(i)
            val webTitle = item.getString("webTitle")
            val webUrl = item.getString("webUrl")
            val data = Data(webTitle,webUrl)
            list.add(data)
        }

        val adapter = NewsAdapter(list)
        binding.listView.adapter = adapter
    }
}