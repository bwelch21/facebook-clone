package com.brandtwelch.pictureme.userprofile

import com.brandtwelch.pictureme.arch.MviViewState
import com.brandtwelch.pictureme.db.entities.Profile

data class UserProfileViewState(
    var isCurrentUser: Boolean = true,
    var profile: Profile = Profile(0, "Brandt", "Welch")
) : MviViewState {
    fun default(): UserProfileViewState {
        return UserProfileViewState(
            isCurrentUser = false,
            profile = Profile(0, "Brandt", "Welch")
        )
    }
}