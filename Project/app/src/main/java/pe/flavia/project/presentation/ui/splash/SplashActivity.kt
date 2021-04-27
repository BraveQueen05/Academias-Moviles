package pe.flavia.project.presentation.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import pe.flavia.project.databinding.ActivitySplashBinding
import pe.flavia.project.presentation.ui.home.HomeActivity
import java.util.*
import kotlin.concurrent.timerTask

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(this.binding.root)
        setUpView()
    }

    private fun setUpView(){
        this.binding.img.imageAnimation
        Timer().schedule(timerTask {
            startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
        },2000)
    }

    private val View.imageAnimation: Unit
        get() {
            val set = AnimationSet(true)
            val animation = ScaleAnimation(0.7f, 1f, 0.7f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
            set.addAnimation(animation)

            val animAlpha: Animation = AlphaAnimation(0.0f, 1.0f)
            set.addAnimation(animAlpha)

            set.duration = 2000
            set.interpolator = OvershootInterpolator(1.3f)
            this.startAnimation(set)
        }
}