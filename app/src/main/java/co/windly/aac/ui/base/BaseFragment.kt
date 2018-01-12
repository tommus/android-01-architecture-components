package co.windly.aac.ui.base

import android.content.Context
import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.AndroidSupportInjection
import dagger.android.support.DaggerFragment

abstract class BaseFragment<out T : ViewDataBinding, out V : BaseViewModel<*>> : DaggerFragment() {

  private var activity: BaseActivity<*, *>? = null
  private lateinit var viewDataBinding: T
  private var viewModel: V? = null
  private var rootView: View? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    this.performDependencyInjection()
    super.onCreate(savedInstanceState)
    this.viewModel = this.getViewModel()
    this.setHasOptionsMenu(false)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    this.viewDataBinding = DataBindingUtil.inflate(inflater, this.getLayoutId(), container, false)
    this.rootView = this.viewDataBinding.root
    return this.rootView
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    this.viewDataBinding.setVariable(this.getBindingVariable(), this.viewModel)
    this.viewDataBinding.executePendingBindings()
  }

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

  fun getViewDataBinding() = this.viewDataBinding

  fun isNetworkConnected(): Boolean {
    return this.activity != null && this.activity!!.isNetworkConnected()
  }

  fun hideKeyboard() {
    this.activity?.hideKeyboard()
  }

  private fun performDependencyInjection() {
    AndroidSupportInjection.inject(this)
  }

  interface Callback {

    fun onFragmentAttached()

    fun onFragmentDetached(tag: String)
  }

  abstract fun getViewModel(): V

  abstract fun getBindingVariable(): Int

  @LayoutRes
  abstract fun getLayoutId(): Int
}
