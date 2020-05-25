package com.brandtwelch.pictureme.arch


interface MviView {
    fun sendIntent(intent: MviIntent)

    fun render(state: MviViewState)
}
