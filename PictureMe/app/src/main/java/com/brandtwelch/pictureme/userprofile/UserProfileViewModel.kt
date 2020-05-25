package com.brandtwelch.pictureme.userprofile

import androidx.lifecycle.MutableLiveData
import com.brandtwelch.pictureme.arch.MviIntent
import com.brandtwelch.pictureme.arch.MviViewModel
import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Profile
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
    }

    override fun states(): MutableLiveData<MviViewState> {
        val defaultState = UserProfileViewState()
        defaultState.isCurrentUser = isCurrentUserProfile(defaultState.profile)

        stateLiveData.value = defaultState

        return stateLiveData
    }

    private fun isCurrentUserProfile(profile: Profile): Boolean {
        //TODO: implement a user session object that holds session info such as the [Profile] objject of the current user
        return true
    }
}