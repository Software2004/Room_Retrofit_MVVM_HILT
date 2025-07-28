package com.example.roomretrofitmvvm.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("app:imageUrl")
fun ImageView.loadImage(url: String) {
    Picasso.get().load(url).into(this)
}