package com.joaovicttors.user_feature.presentation.components

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.joaovicttors.user_feature.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

@BindingAdapter("imageUrl", "error", "callback", requireAll = true)
fun CircleImageView.imageUrl(imageUrl: String?, error: Drawable, callback: Callback) {
    Picasso.get()
        .load(imageUrl)
        .error(error)
        .into(this, callback)
}