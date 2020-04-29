package com.brandtwelch.pictureme

import android.app.Activity
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import kotlinx.coroutines.*

class SplashActivity : Activity() {

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

        val output = withContext(Dispatchers.IO) {
            Thread.sleep(3 * 1000)
            true
        }

        if (output) {
            val intent = KnowMeActivity.getLaunchIntent(baseContext)
            startActivity(intent)
        }
    }

    private fun showLoading() {

        val loadingImageView = findViewById<ImageView>(R.id.loading_spinner)
        val loadingAnimationDrawable = loadingImageView.drawable as AnimationDrawable
        loadingAnimationDrawable.start()
    }
}