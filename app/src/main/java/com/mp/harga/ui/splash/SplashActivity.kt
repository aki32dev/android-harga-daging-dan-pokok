package com.mp.harga.ui.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatDelegate
import com.mp.harga.databinding.ActivitySplashBinding
import com.mp.harga.ui.base.BaseActivity
import com.mp.harga.utils.contant.Constant
import com.mp.harga.utils.extensions.goToMain

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding

    override fun initLayout(): View {
        binding = ActivitySplashBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root
    }

    override fun initView() {
        super.initView()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        Handler(Looper.getMainLooper()).postDelayed({
            goToMain()
            finish()
        }, Constant.SPLASH_TIME_OUT)

    }
}