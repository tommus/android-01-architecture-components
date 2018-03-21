package co.windly.aac.ui.main.covers.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.windly.aac.data.domain.models.covers.Cover
import co.windly.aac.databinding.ItemCoversListBinding
import co.windly.aac.databinding.ItemEmptyListBinding
import co.windly.aac.ui.base.BaseViewHolder
import co.windly.aac.ui.main.empty.EmptyItemViewHolder
import co.windly.aac.ui.main.empty.EmptyItemViewModel

class CoversListAdapter : RecyclerView.Adapter<BaseViewHolder> {

  companion object {
    const val VIEW_TYPE_EMPTY = 0
    const val VIEW_TYPE_NORMAL = 1
  }

  private val coversList: MutableList<Cover>
  private lateinit var listener: CoversListAdapterListener

  constructor(coversList: List<Cover>) {
    this.coversList = mutableListOf(*coversList.toTypedArray())
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    holder?.onBind(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val inflater = LayoutInflater.from(parent?.context)

    return when (viewType) {
      VIEW_TYPE_NORMAL -> {
        val binding = ItemCoversListBinding.inflate(inflater, parent, false)
        CoverViewHolder(binding)
      }
      VIEW_TYPE_EMPTY -> {
        val binding = ItemEmptyListBinding.inflate(inflater, parent, false)
        EmptyItemViewHolder(binding, listener)
      }
      else -> {
        throw IllegalArgumentException("Unknown view type.")
      }
    }
  }

  override fun getItemViewType(position: Int): Int
    = if (this.coversList.isNotEmpty()) {
    VIEW_TYPE_NORMAL
  } else {
    VIEW_TYPE_EMPTY
  }

  override fun getItemCount(): Int
    = if (this.coversList.isNotEmpty()) {
    this.coversList.size
  } else {
    1
  }

  fun setListener(listener: CoversListAdapterListener) {
    this.listener = listener
  }

  fun addItems(covers: List<Cover>) {
    this.coversList.addAll(covers)
    this.notifyDataSetChanged()
  }

  fun clearItems() {
    this.coversList.clear()
  }

  inner class CoverViewHolder : BaseViewHolder, CoversListItemViewModel.CoversListItemViewModelListener {

    private lateinit var coversListItemViewModel: CoversListItemViewModel
    private val binding: ItemCoversListBinding

    constructor(binding: ItemCoversListBinding) : super(binding.root) {
      this.binding = binding
    }

    override fun onBind(position: Int) {
      val cover = coversList[position]
      this.coversListItemViewModel = CoversListItemViewModel(cover, this)
      this.binding.viewModel = this.coversListItemViewModel
      this.binding.executePendingBindings()
    }

    override fun onDeleteClick(coverId: Long) {
      listener.onDeleteClick(coverId)
    }
  }

  interface CoversListAdapterListener : EmptyItemViewModel.EmptyItemViewModelListener {
    fun onDeleteClick(coverId: Long)
  }
}
