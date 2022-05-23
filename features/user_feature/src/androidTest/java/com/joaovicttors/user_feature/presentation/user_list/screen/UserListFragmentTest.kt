package com.joaovicttors.user_feature.presentation.user_list.screen

import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.joaovicttors.user_feature.R
import com.joaovicttors.user_feature.domain.usescases.GetUserListUseCase
import com.joaovicttors.user_feature.presentation.user_list.UserListState
import com.joaovicttors.user_feature.utils.DataBindingIdlingResourceRule
import com.joaovicttors.user_feature.utils.monitorFragment
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class UserListFragmentTest {

    @get:Rule
    val dataBindingIdlingResource = DataBindingIdlingResourceRule()

    private lateinit var viewModelMock: UserListViewModel

    @Before
    fun before() {
        viewModelMock = mockk()

        loadKoinModules(module {
            viewModel { viewModelMock }
        })
    }

    @Test
    fun test123() {
        every { viewModelMock.state } returns mockk()
        every { viewModelMock.getUserList() } returns Unit


        val scenario = launchFragmentInContainer() {
            UserListFragment()
        }

        dataBindingIdlingResource.idlingResource.monitorFragment(scenario)

        onView(withId(R.id.title)).check(matches(isDisplayed()))

        scenario.close()
    }
}