package com.joaovicttors.user_feature.presentation.user_list.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserListFragment : Fragment(), KoinComponent {

    private val viewModel: UserListViewModel by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserList()
    }
}