package com.brandtwelch.pictureme.userprofile

import androidx.lifecycle.MutableLiveData
import com.brandtwelch.pictureme.R
import com.brandtwelch.pictureme.arch.MviIntent
import com.brandtwelch.pictureme.arch.MviViewModel
import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Post
import com.brandtwelch.pictureme.db.entities.Profile
import java.sql.Timestamp
import java.util.logging.Level
import java.util.logging.Logger

class UserProfileViewModel : MviViewModel() {

    private val stateLiveData: MutableLiveData<MviViewState> by lazy {
        MutableLiveData<MviViewState>()
    }

    companion object {
        @JvmStatic
        val LOGGER: Logger? = Logger.getLogger(UserProfileViewModel::class.java.canonicalName)
    }

    private fun reduce(intent: UserProfileIntent) {
        // TODO: map intents to business logic
    }

    override fun processIntent(intent: MviIntent) {
        LOGGER?.log(Level.INFO, String.format("Intent received: %s", intent))

        val currentState = stateLiveData.value as UserProfileViewState

        when (intent) {
            UserProfileIntent.ACTIVITY_STARTED -> { }
            UserProfileIntent.TIMELINE_FRAGMENT_CREATED -> {
                stateLiveData.value = currentState.copy()
            }
            UserProfileIntent.EDIT_PROFILE_BUTTON_PRESSED -> { }
            else -> {
                LOGGER?.log(Level.WARNING, String.format("Unknown intent ( % ) received", intent))
            }
        }
    }

    override fun states(): MutableLiveData<MviViewState> {
        val defaultState = UserProfileViewState()
        defaultState.isCurrentUser = isCurrentUserProfile(defaultState.profile)
        defaultState.timelineItems = generateDummyPosts()

        stateLiveData.value = defaultState

        return stateLiveData
    }

    private fun generateDummyPosts(): ArrayList<Post> {
        val posts = arrayListOf<Post>()

        for (i in 0..10) {
            val post = Post(
                id = i,
                originalPosterId = i,
                timestamp = Timestamp.valueOf("2020-10-21 19:11:01"),
                postBody = ""
            )
            posts.add(post)
        }

        return posts
    }

    private fun isCurrentUserProfile(profile: Profile): Boolean {
        //TODO: implement a user session object that holds session info such as the [Profile] object of the current user
        return true
    }
}