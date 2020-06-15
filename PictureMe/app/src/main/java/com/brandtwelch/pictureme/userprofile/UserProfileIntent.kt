package com.brandtwelch.pictureme.userprofile

import com.brandtwelch.pictureme.arch.MviIntent

enum class UserProfileIntent : MviIntent {
    ACTIVITY_STARTED,
    TIMELINE_FRAGMENT_CREATED,
    EDIT_PROFILE_BUTTON_PRESSED
}