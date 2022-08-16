package com.mp.harga.utils.extensions

import android.app.Activity
import android.content.Intent
import com.mp.harga.R
import com.mp.harga.ui.main.MainActivity

fun Activity.goToMain() {
    startActivity(
        Intent(
            this,
            MainActivity::class.java
        )
    )
    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
}
