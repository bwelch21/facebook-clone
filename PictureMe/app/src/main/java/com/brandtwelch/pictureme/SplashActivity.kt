package com.brandtwelch.pictureme

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.room.Room
import com.brandtwelch.pictureme.db.KnowMeDatabase
import com.brandtwelch.pictureme.userprofile.UserProfileActivity
import kotlinx.coroutines.*

class SplashActivity : Activity() {

    companion object {
        @JvmStatic
        private var db: KnowMeDatabase? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // need to set the content view, the activity is still not visible to the user
        setContentView(R.layout.splash_layout)
    }

    override fun onResume() {
        super.onResume()
        loadData()
        showLoading()
    }

    private fun loadData() = CoroutineScope(Dispatchers.Main).launch {


        val isGetDataSuccess = mockGetData()

        db = Room.databaseBuilder(
            applicationContext,
            KnowMeDatabase::class.java,
            "know-me"
        ).build()

        if (isGetDataSuccess) {
            val intent = UserProfileActivity.getLaunchIntent(baseContext)
            startActivity(intent)
        }
    }

    private suspend fun mockGetData(): Boolean {
        delay(3 * 1000)
        return true
    }


    private fun showLoading() {

        val loadingImageView = findViewById<ImageView>(R.id.loading_spinner)
        val loadingAnimationDrawable = loadingImageView.drawable as AnimationDrawable
        loadingAnimationDrawable.start()
    }
}