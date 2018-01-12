package co.windly.aac.ui.main.empty

class EmptyItemViewModel {

  private val listener: EmptyItemViewModelListener

  constructor(listener: EmptyItemViewModelListener) {
    this.listener = listener
  }

  fun onRetryClick() {
    this.listener.onRetryClick()
  }

  interface EmptyItemViewModelListener {
    fun onRetryClick()
  }
}