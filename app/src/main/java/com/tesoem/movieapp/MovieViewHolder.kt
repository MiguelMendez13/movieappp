package com.tesoem.movieapp

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tesoem.movieapp.databinding.ItemMovieBinding
import java.security.AccessController.getContext

class MovieViewHolder(view:View):RecyclerView.ViewHolder(view) {
    private  val binding = ItemMovieBinding.bind(view)

    fun bind(image:String,txtName:String,ids:String){
        Picasso.get().load(image).into(binding.ivPoster)
        binding.tvNombre.text = txtName

        itemView.setOnClickListener {
            Toast.makeText(binding.ivPoster.context, txtName, Toast.LENGTH_SHORT).show()

            val context = binding.ivPoster.context;

            val intent = Intent(context,MovieInfo::class.java )

            intent.putExtra("title", txtName)
            intent.putExtra("idkey",ids)

            context.startActivity(intent)
        }


    }



}