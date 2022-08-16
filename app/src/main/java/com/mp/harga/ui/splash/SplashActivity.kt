package com.mp.harga.ui.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatDelegate
import com.mp.harga.R
import com.mp.harga.databinding.ActivitySplashBinding
import com.mp.harga.ui.base.BaseActivity
import com.mp.harga.utils.contant.Constant
import com.mp.harga.utils.extensions.goToMain

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private var _binding: ActivitySplashBinding? = null
    private val binding get() = _binding!!

    override fun initLayout(): View {
        _binding = ActivitySplashBinding.inflate(layoutInflater)
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        return binding.root
    }

    override fun initView() {
        super.initView()
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        val animation = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        binding.llLogoApps.animation = animation
        Handler(Looper.getMainLooper()).postDelayed({
            goToMain()
            finish()
        }, Constant.SPLASH_TIME_OUT)

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}