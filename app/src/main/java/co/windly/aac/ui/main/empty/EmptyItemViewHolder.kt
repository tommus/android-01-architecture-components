package co.windly.aac.ui.main.empty

import android.graphics.drawable.Animatable
import co.windly.aac.databinding.ItemEmptyListBinding
import co.windly.aac.ui.base.BaseViewHolder

class EmptyItemViewHolder : BaseViewHolder, EmptyItemViewModel.EmptyItemViewModelListener {

  private val binding: ItemEmptyListBinding
  private val listener: EmptyItemViewModel.EmptyItemViewModelListener

  constructor(
    binding: ItemEmptyListBinding,
    listener: EmptyItemViewModel.EmptyItemViewModelListener
  ) : super(binding.root) {
    this.binding = binding
    this.listener = listener
  }

  override fun onBind(position: Int) {
    this.binding.viewModel = EmptyItemViewModel(this)
    if (this.binding.image.drawable is Animatable) {
      (this.binding.image.drawable as Animatable).start()
    }
  }

  override fun onRetryClick() {
    this.listener.onRetryClick()
  }
}
