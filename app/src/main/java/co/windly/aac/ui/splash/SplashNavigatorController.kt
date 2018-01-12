package co.windly.aac.ui.splash

import android.content.Context
import android.content.Intent
import android.os.Bundle
import co.windly.aac.ui.main.MainActivity
import javax.inject.Inject

class SplashNavigatorController : SplashNavigator {

  private val context: Context

  @Inject
  constructor(splashActivity: SplashActivity) {
    this.context = splashActivity
  }

  override fun showMainView() {
    val intent = Intent(this.context, MainActivity::class.java)
    val bundle = Bundle()
    this.context.startActivity(intent, bundle)
  }
}
