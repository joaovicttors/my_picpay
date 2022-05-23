package com.joaovicttors.user_feature.presentation.user_list.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    private lateinit var viewAdapter: UserListAdapter
    private lateinit var dataBinding: FragmentUserListBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        dataBinding = FragmentUserListBinding.inflate(inflater, container, false)
        viewAdapter = UserListAdapter()

        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycleViewFactory()
        viewStateObserver()

        viewModel.getUserList()
    }

    private fun recycleViewFactory() {
        val layoutManager = LinearLayoutManager(context)
        layoutManager.orientation = LinearLayoutManager.VERTICAL

        dataBinding.recyclerView.adapter = viewAdapter
        dataBinding.recyclerView.layoutManager = layoutManager
    }

    private fun viewStateObserver() {
        lifecycleScope.launch { ->
            repeatOnLifecycle(Lifecycle.State.STARTED) { ->
                viewModel.state.collect { viewState ->
                    viewAdapter.users = viewState.data

                    loadingStateBehavior(viewState.isLoading)
                    errorStateBehavior(viewState.errorMessage)
                }
            }
        }
    }

    private fun loadingStateBehavior(isLoading: Boolean) {
        if (isLoading) {
            dataBinding.shimmer.startShimmer()
            dataBinding.shimmer.visibility = View.VISIBLE
        } else {
            dataBinding.shimmer.stopShimmer()
            dataBinding.shimmer.visibility = View.GONE
        }
    }

    private fun errorStateBehavior(errorMessage: String?) {
        if (errorMessage != null) {
            Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}