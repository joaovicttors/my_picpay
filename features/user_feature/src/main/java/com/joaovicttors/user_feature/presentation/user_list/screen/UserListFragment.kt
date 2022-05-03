package com.joaovicttors.user_feature.presentation.user_list.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.joaovicttors.user_feature.databinding.FragmentUserListBinding
import com.joaovicttors.user_feature.presentation.user_list.adapter.UserListAdapter
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class UserListFragment : Fragment(), KoinComponent {

    private val viewModel: UserListViewModel by inject()
    private val viewAdapter: UserListAdapter by lazy { UserListAdapter() }

    private lateinit var dataBinding: FragmentUserListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentUserListBinding.inflate(inflater, container, false)

        // TODO joao.santana
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { viewAdapter.users = it.data; Log.d("TEST_TEST", it.data.toString()) }
            }
        }

        viewModel.getUserList()

        recycleViewFactory()

        return dataBinding.root
    }

    private fun recycleViewFactory() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        dataBinding.recyclerView.adapter = viewAdapter
        dataBinding.recyclerView.layoutManager = layoutManager
    }
}