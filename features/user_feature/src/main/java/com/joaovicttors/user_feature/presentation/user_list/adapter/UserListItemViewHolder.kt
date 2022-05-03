package com.joaovicttors.user_feature.presentation.user_list.adapter

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.joaovicttors.user_feature.BR
import com.joaovicttors.user_feature.domain.entities.User

class UserListItemViewHolder(
    private val dataBinding: ViewDataBinding
) : RecyclerView.ViewHolder(dataBinding.root) {

    fun bind(user: User) {
        dataBinding.setVariable(BR.user, user)
        dataBinding.executePendingBindings()
    }
}