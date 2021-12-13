package com.jesil.card.ui.utils

import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide

infix fun ImageView.loadCardType(path: Int){
    Glide.with(this)
        .load(path)
        .into(this)
}