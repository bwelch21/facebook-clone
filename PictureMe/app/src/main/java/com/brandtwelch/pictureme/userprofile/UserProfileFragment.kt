package com.brandtwelch.pictureme.userprofile

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.brandtwelch.pictureme.R
import com.brandtwelch.pictureme.arch.MviIntent
import com.brandtwelch.pictureme.arch.MviView
import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Post
import java.util.logging.Level
import java.util.logging.Logger

class UserProfileFragment : Fragment(), MviView {

    private lateinit var viewModel: UserProfileViewModel
    private lateinit var recyclerView: View

    private lateinit var timeline: ArrayList<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProvider(this,
                ViewModelProvider.NewInstanceFactory()).get(UserProfileViewModel::class.java)
        } ?: throw Exception("Invalid Activity")

        viewModel.states().observe(this, Observer {
            LOGGER?.log(Level.INFO, "State changed and render called")
            render(it)
        })

        sendIntent(UserProfileIntent.TIMELINE_FRAGMENT_CREATED)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buildFeed(timeline)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun render(state: MviViewState) {
        if (state !is UserProfileViewState) return

        timeline = state.timelineItems
    }

    override fun sendIntent(intent: MviIntent) {
        UserProfileActivity.LOGGER?.log(Level.INFO, String.format("%s intent sent", intent))
        viewModel.processIntent(intent)
    }

    private fun buildFeed(timelinePosts: ArrayList<Post>) {
        timelinePosts.forEach {
            it.postBody.plus(resources.getString(R.string.dummy_body))
        }

        recyclerView = (activity?.findViewById(R.id.user_feed_recycler_view) as RecyclerView).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(activity)
            adapter = FeedAdapter(timelinePosts)
        }
    }



    companion object {
        @JvmStatic
        fun newInstance() = UserProfileFragment()

        @JvmStatic
        val LOGGER: Logger? = Logger.getLogger(UserProfileFragment::class.java.canonicalName)
    }
}
