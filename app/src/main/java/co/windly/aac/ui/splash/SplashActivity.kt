package co.windly.aac.ui.splash

import android.graphics.drawable.Animatable
import android.os.Bundle
import co.windly.aac.R
import co.windly.aac.databinding.ActivitySplashBinding
import co.windly.aac.ui.base.BaseActivity
import com.android.databinding.library.baseAdapters.BR
import javax.inject.Inject

class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

  @Inject
  lateinit var splashViewModel: SplashViewModel

  private lateinit var activitySplashBinding: ActivitySplashBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.activitySplashBinding = this.getViewDataBinding()
    this.splashViewModel.initialize()
    this.animateLogo()
  }

  override fun getViewModel(): SplashViewModel
    = this.splashViewModel

  override fun getBindingVariable(): Int
    = BR.viewModel

  override fun getLayoutId(): Int
    = R.layout.activity_splash

  private fun animateLogo() {
    if (this.activitySplashBinding.logo.drawable is Animatable) {
      (this.activitySplashBinding.logo.drawable as Animatable).start()
    }
  }
}
