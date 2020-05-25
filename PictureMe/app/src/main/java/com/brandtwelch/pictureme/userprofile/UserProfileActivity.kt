package com.brandtwelch.pictureme.userprofile

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.brandtwelch.pictureme.R
import com.brandtwelch.pictureme.arch.MviIntent
import com.brandtwelch.pictureme.arch.MviView
import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Post
import java.sql.Timestamp
import java.util.logging.Level
import java.util.logging.Logger

class UserProfileActivity : AppCompatActivity(), MviView {

    private lateinit var viewModel: UserProfileViewModel

    private lateinit var profileNameView: TextView
    private lateinit var editProfileButton: Button
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    companion object {
        @JvmStatic
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, UserProfileActivity::class.java)
        }

        @JvmStatic
        val LOGGER: Logger? = Logger.getLogger(UserProfileActivity::class.java.canonicalName)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_profile_layout)
        bindViews()
        setOnClickListeners()
        builFeed()

        viewModel = ViewModelProvider(this,
            ViewModelProvider.NewInstanceFactory()).get(UserProfileViewModel::class.java)
        viewModel.states().observe(this, Observer {
            LOGGER?.log(Level.INFO, "State changed and render called")
            render(it)
        })

        sendIntent(UserProfileIntent.ACTIVITY_STARTED)
    }

    override fun onResume() {
        super.onResume()

    }

    private fun bindViews() {
        profileNameView = findViewById(R.id.full_profile_name)
        editProfileButton = findViewById(R.id.edit_profile_button)
    }

    private fun builFeed() {
        recyclerView = findViewById<RecyclerView>(R.id.user_feed_recycler_view).apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this@UserProfileActivity)
            adapter = FeedAdapter(generateDummyPosts())
        }
    }

    private fun generateDummyPosts(): ArrayList<Post> {
        val posts = arrayListOf<Post>()

        for (i in 0..10) {
            val post = Post(
                id = i,
                originalPosterId = i,
                timestamp = Timestamp.valueOf("2020-10-21 19:11:01"),
                postBody = resources.getString(R.string.dummy_body)
            )
            posts.add(post)
        }

        return posts
    }

    private fun setOnClickListeners() {
        editProfileButton.setOnClickListener {
            LOGGER?.log(Level.INFO, "Edit profile button pressed")
            sendIntent(UserProfileIntent.EDIT_PROFILE_BUTTON_PRESSED)
        }
    }


    override fun sendIntent(intent: MviIntent) {
        LOGGER?.log(Level.INFO, String.format("%s intent sent", intent))
        viewModel.processIntent(intent)
    }

    override fun render(state: MviViewState) {
        if (state !is UserProfileViewState) return

        profileNameView.text = String.format("%s %s", state.profile.firstName, state.profile.lastName)
        editProfileButton.visibility = if (state.isCurrentUser) View.VISIBLE else View.GONE
    }
}