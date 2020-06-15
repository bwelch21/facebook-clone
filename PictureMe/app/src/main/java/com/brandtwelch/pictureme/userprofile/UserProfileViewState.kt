package com.brandtwelch.pictureme.userprofile

import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Post
import com.brandtwelch.pictureme.db.entities.Profile

data class UserProfileViewState(
    var isCurrentUser: Boolean = true,
    var profile: Profile = Profile(0, "Brandt", "Welch"),
    var timelineItems: ArrayList<Post> = ArrayList()
) : MviViewState {
    fun default(): UserProfileViewState {
        return UserProfileViewState(
            isCurrentUser = false,
            profile = Profile(0, "Brandt", "Welch"),
            timelineItems = ArrayList()
        )
    }
}