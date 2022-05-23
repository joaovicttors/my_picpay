package com.joaovicttors.user_feature.presentation.user_list.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.user_feature.databinding.ListItemUserBinding
import com.joaovicttors.user_feature.domain.entities.User
import com.squareup.picasso.Callback

class UserListItemViewHolder(
    private val dataBinding: ListItemUserBinding
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(user: User) {
        dataBinding.progressBar.visibility = View.VISIBLE

        dataBinding.user = user
        dataBinding.callback = object : Callback {
            override fun onSuccess() {
                dataBinding.progressBar.visibility = View.GONE
            }

            override fun onError(e: Exception?) {
                dataBinding.progressBar.visibility = View.GONE
            }
        }

        dataBinding.executePendingBindings()
    }
}