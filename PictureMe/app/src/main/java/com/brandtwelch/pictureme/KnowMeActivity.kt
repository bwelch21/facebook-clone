package com.brandtwelch.pictureme

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class KnowMeActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun getLaunchIntent(context: Context): Intent {
            return Intent(context, KnowMeActivity::class.java)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
