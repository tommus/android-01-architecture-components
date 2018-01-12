package co.windly.aac

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ViewModelProviderFactory<V : Any> : ViewModelProvider.Factory {

  private val viewModel: V

  constructor(viewModel: V) {
    this.viewModel = viewModel
  }

  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(this.viewModel.javaClass)) {
      return this.viewModel as T
    }
    throw IllegalArgumentException("Unknown class name.")
  }
}
