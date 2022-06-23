package com.tesoem.movieapp

import android.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.squareup.picasso.Picasso
import com.tesoem.movieapp.databinding.ActivityMainBinding
import com.tesoem.movieapp.databinding.ActivityMovieInfoBinding
import dataModelOverview.APIservicesOver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.math.log

class MovieInfo : AppCompatActivity() {
    private  lateinit var binding: ActivityMovieInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val actionBar: androidx.appcompat.app.ActionBar? = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar!!.setDisplayShowHomeEnabled(true)

        var intent = intent

        val title = intent.getStringExtra("title")

        actionBar.setTitle(title)
        findOverview()
    }
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://online-movie-database.p.rapidapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(getClient())
            .build()
    }

    private fun getClient(): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(HeaderInterceptor())
            .build()

    private fun findOverview(){
        var idkey = intent.getStringExtra("idkey")
        CoroutineScope(Dispatchers.IO).launch{
        var inf = binding.info
        val call = getRetrofit().create(APIservicesOver::class.java).getData("title/get-overview-details?tconst=$idkey&currentCountry=US")
        val info = call.body()
            if(call.isSuccessful){

                val image = info?.title?.image?.url
                val rev  = info?.plotOutline?.text
                val ratingVal = info?.ratings?.rating
                val rtinMinutes = info?.title?.runningTimeInMinutes
                val tp = info?.title?.titleType
                val rankMovie = info?.ratings?.topRank
                this@MovieInfo.runOnUiThread(java.lang.Runnable {
                    binding.info.setText(rev)
                    Picasso.get().load(image).into(binding.ivPoster)
                    binding.duration.setText(rtinMinutes.toString())
                    binding.rating.setText(ratingVal.toString())
                    binding.topRank.setText(rankMovie.toString())
                })

            }else{

            }
        }

    }


}