package com.muflihunnisa.movieapp.viewmodel


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpClient.log
import com.loopj.android.http.AsyncHttpResponseHandler
import com.muflihunnisa.movieapp.model.Model
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class ContentVM : ViewModel() {
    companion object {
        private const val API_KEY = "da8f5c08252b23e2c39df166b9b0f34c"
    }

    private val listContent = MutableLiveData<ArrayList<Model>>()

    internal fun setCatalogueContentMovie(category: String = "movie") {
        val client = AsyncHttpClient()
        val listItem = ArrayList<Model>()
        val BASE_URL = "https://api.themoviedb.org/3/discover/movie?api_key=da8f5c08252b23e2c39df166b9b0f34c"

        client.get(BASE_URL, object : AsyncHttpResponseHandler() {
            override fun onSuccess(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray
            ) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("results")

                    for (i in 0 until list.length()) {
                        val movies = list.getJSONObject(i)
                        val moviesItem = Model()
                        moviesItem.id = movies.getInt("id")
                        if (movies.has("title")) {
                            moviesItem.name = movies.getString("title")
                        } else {
                            moviesItem.name = movies.getString("name")
                        }
                        moviesItem.desc = movies.getString("overview")
                        if (movies.has("first_launching")) {
                            moviesItem.year = movies.getString("first_launching")
                        } else {
                            moviesItem.year = movies.getString("release_date")
                        }
                        if (movies.has("banner")){
                            moviesItem.banner = movies.getString("main_banner")
                        } else {
                            moviesItem.thumb = movies.getString("poster_path")
                            listItem.add(moviesItem)
                        }
                    }
                    listContent.postValue(listItem)
                } catch (e: Exception) {
                    print(msg = e.message.toString())
                }
            }

            override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>,
                    responseBody: ByteArray,
                    error: Throwable
            ) {
                print(msg = error.message.toString())
            }
        })
    }

    internal fun getCatalogueContent(): LiveData<ArrayList<Model>> {
        return listContent
    }

    private fun print(msg: String, tag: String? = "tagCatalogueContent") {
        log.e(tag, msg)
    }

}
