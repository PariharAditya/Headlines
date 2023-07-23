package com.example.recyclerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.toolbox.JsonObjectRequest
import com.example.recyclerapp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(), NewsItem
{
    private lateinit var binding: ActivityMainBinding
    private lateinit var madapter: ApplicationAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        fetchJson()

        madapter = ApplicationAdapter(this)
        binding.recyclerView.adapter = madapter
    }
    private fun fetchJson() {
        val url = ""

        val jsonObjectRequest = object : JsonObjectRequest(
                Method.GET, url, null,
                { response ->
                    // Handle the JSON response here
                    // You can access the JSON data using the 'response' parameter
                    val newsJsonArray = response.getJSONArray("articles")
                    val newsArray = ArrayList<News>()
                    for (i in 0 until newsJsonArray.length()) {
                        val newsJsonObject = newsJsonArray.getJSONObject(i)
                        val news = News(
                                newsJsonObject.getString("title"),
                                newsJsonObject.getString("author"),
                                newsJsonObject.getString("url"),
                                newsJsonObject.getString("urlToImage")
                        )
                        newsArray.add(news)
                    }
                    madapter.updateNews(newsArray)
                },
                {
                    // Handle error cases here
                    // You can access the error information using the 'error' parameter
                }) {
            //When normal API call gives 403 error
            override fun getHeaders(): MutableMap<String, String> {
                val headers = HashMap<String, String>()
                headers["User-Agent"] = "Mozilla/5.0"
                return headers
            }
        }

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }




}
