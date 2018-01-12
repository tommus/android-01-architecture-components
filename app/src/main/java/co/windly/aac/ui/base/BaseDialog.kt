package co.windly.aac.ui.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v4.app.FragmentManager
import android.view.ViewGroup
import android.view.Window
import android.widget.RelativeLayout

abstract class BaseDialog : DialogFragment() {

  private var activity: BaseActivity<*, *>? = null

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is BaseActivity<*, *>) {
      this.activity = context
      this.activity?.onFragmentAttached()
    }
  }

  override fun onDetach() {
    this.activity = null
    super.onDetach()
  }

  fun getBaseActivity() = this.activity

  override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
    // Content.
    val root = RelativeLayout(this.activity)
    root.layoutParams = ViewGroup.LayoutParams(
      ViewGroup.LayoutParams.MATCH_PARENT,
      ViewGroup.LayoutParams.WRAP_CONTENT)
    // Fullscreen dialog.
    val dialog = Dialog(this.context)
    dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
    dialog.setContentView(root)
    if (dialog.window != null) {
      dialog.window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
      dialog.window.setLayout(
        ViewGroup.LayoutParams.MATCH_PARENT,
        ViewGroup.LayoutParams.WRAP_CONTENT)
      dialog.setCanceledOnTouchOutside(false)
    }
    return dialog
  }

  fun showLoading() {
    this.activity?.showLoading()
  }

  fun hideLoading() {
    this.activity?.hideLoading()
  }

  fun isNetworkConnected(): Boolean {
    return this.activity != null && this.activity!!.isNetworkConnected()
  }

  fun hideKeyboard() {
    this.activity?.hideKeyboard()
  }

  override fun show(fragmentManager: FragmentManager, tag: String) {
    val transaction = fragmentManager.beginTransaction()
    val previousFragment = fragmentManager.findFragmentByTag(tag)
    if (previousFragment != null) {
      transaction.remove(previousFragment)
    }
    transaction.addToBackStack(null)
    this.show(transaction, tag)
  }

  fun dismissDialog(tag: String) {
    this.dismiss()
    this.getBaseActivity()?.onFragmentDetached(tag)
  }
}
