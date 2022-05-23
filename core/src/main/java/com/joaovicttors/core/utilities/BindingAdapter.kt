package com.joaovicttors.core.utilities

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.joaovicttors.core.R
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl", "error")
fun loadImage(view: CircleImageView, url: String?, error: Drawable) {
    Picasso.get()
        .load(url)
        .error(error)
        .into(view)
}

@BindingAdapter("bind:imageUrl")
fun imageUrl(imageView: CircleImageView, imageUrl: String?) {
    Picasso.get()
        .load(imageUrl)
        .into(imageView)
}