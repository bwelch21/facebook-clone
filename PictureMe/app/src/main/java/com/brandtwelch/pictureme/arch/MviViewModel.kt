package com.brandtwelch.pictureme.arch

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class MviViewModel : ViewModel() {
    abstract fun processIntent(intent: MviIntent)

    abstract fun states(): MutableLiveData<MviViewState>
}
