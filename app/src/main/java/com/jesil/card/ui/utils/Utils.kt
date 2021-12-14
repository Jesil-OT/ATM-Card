package com.jesil.card.ui.utils

import android.view.View
import android.widget.ImageView
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar

infix fun ImageView.loadCardType(path: Int) {
    Glide.with(this)
        .load(path)
        .into(this)
}

infix fun View.state(state: Boolean) {
    this.isVisible = state
}

infix fun View.messageToUser(message: String?) {
    if (message != null) {
        Snackbar.make(this, message, Snackbar.LENGTH_LONG).show()
    }
}