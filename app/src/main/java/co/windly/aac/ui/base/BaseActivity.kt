package co.windly.aac.ui.base

import android.annotation.TargetApi
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.inputmethod.InputMethodManager
import co.windly.aac.R
import co.windly.aac.utilities.CommonUtils
import co.windly.aac.utilities.NetworkUtils
import dagger.android.AndroidInjection
import dagger.android.support.DaggerAppCompatActivity

abstract class BaseActivity<out T : ViewDataBinding, out V : BaseViewModel<*>> : DaggerAppCompatActivity(), BaseFragment.Callback {

  private lateinit var viewDataBinding: T
  private var viewModel: V? = null

  private var progressDialog: ProgressDialog? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.performDependencyInjection()
    this.performDataBinding()
  }

  override fun onBackPressed() {
    super.onBackPressed()
    this.overridePendingTransition(R.anim.activity_enter_ios_alike_from_left, R.anim.actibity_exit_ios_alike_to_right)
  }

  override fun startActivity(intent: Intent?, options: Bundle?) {
    super.startActivity(intent, options)
    this.overridePendingTransition(R.anim.activity_enter_ios_alike_from_right, R.anim.activity_exit_ios_alike_to_left)
  }

  private fun performDataBinding() {
    this.viewDataBinding = DataBindingUtil.setContentView(this, this.getLayoutId())
    this.viewModel = if (this.viewModel == null) this.getViewModel() else this.viewModel
    this.viewDataBinding.setVariable(this.getBindingVariable(), this.viewModel)
    this.viewDataBinding.executePendingBindings()
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun requestPermissionsSafely(vararg permissions: String, requestCode: Int) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      this.requestPermissions(permissions, requestCode)
    }
  }

  @TargetApi(Build.VERSION_CODES.M)
  fun hasPermission(permission: String): Boolean {
    return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
      this.checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
  }

  override fun onFragmentAttached() {
    // No-op.
  }

  override fun onFragmentDetached(tag: String) {
    // No-op.
  }

  fun hideKeyboard() {
    this.currentFocus?.let {
      val inputMethodManager = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
      inputMethodManager.hideSoftInputFromWindow(it.windowToken, 0)
    }
  }

  fun isNetworkConnected() = NetworkUtils.isNetworkConnected(this.applicationContext)

  fun showLoading() {
    this.hideLoading()
    this.progressDialog = CommonUtils.showLoadingDialog(this)
  }

  fun hideLoading() {
    this.progressDialog?.let {
      if (it.isShowing) {
        it.cancel()
      }
    }
  }

  fun getViewDataBinding() = this.viewDataBinding

  abstract fun getViewModel(): V

  abstract fun getBindingVariable(): Int

  @LayoutRes
  abstract fun getLayoutId(): Int

  open fun performDependencyInjection() {
    AndroidInjection.inject(this)
  }
}
