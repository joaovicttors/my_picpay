package com.joaovicttors.user_feature.presentation.user_list.screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.joaovicttors.user_feature.databinding.FragmentUserListBinding
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserListFragment : Fragment(), KoinComponent {

    private val viewModel: UserListViewModel by inject()

    private lateinit var binding: FragmentUserListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentUserListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserList()
    }
}