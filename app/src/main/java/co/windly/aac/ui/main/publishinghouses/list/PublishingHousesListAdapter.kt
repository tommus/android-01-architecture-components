package co.windly.aac.ui.main.publishinghouses.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse
import co.windly.aac.databinding.ItemEmptyListBinding
import co.windly.aac.databinding.ItemPublishingHousesListBinding
import co.windly.aac.ui.base.BaseViewHolder
import co.windly.aac.ui.main.covers.list.CoversListAdapter
import co.windly.aac.ui.main.empty.EmptyItemViewHolder
import co.windly.aac.ui.main.empty.EmptyItemViewModel

class PublishingHousesListAdapter : RecyclerView.Adapter<BaseViewHolder> {

  companion object {
    const val VIEW_TYPE_EMPTY = 0
    const val VIEW_TYPE_NORMAL = 1
  }

  private val publishingHousesList: MutableList<PublishingHouse>
  private lateinit var listener: PublishingHousesListAdapterListener

  constructor(publishingHousesList: List<PublishingHouse>) {
    this.publishingHousesList = mutableListOf(*publishingHousesList.toTypedArray())
  }

  override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
    holder?.onBind(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
    val inflater = LayoutInflater.from(parent?.context)

    return when (viewType) {
      VIEW_TYPE_NORMAL -> {
        val binding = ItemPublishingHousesListBinding.inflate(inflater, parent, false)
        PublishingHouseViewHolder(binding)
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
    = if (this.publishingHousesList.isNotEmpty()) {
    CoversListAdapter.VIEW_TYPE_NORMAL
  } else {
    CoversListAdapter.VIEW_TYPE_EMPTY
  }

  override fun getItemCount(): Int
    = if (this.publishingHousesList.isNotEmpty()) {
    this.publishingHousesList.size
  } else {
    1
  }

  fun setListener(listener: PublishingHousesListAdapterListener) {
    this.listener = listener
  }

  fun addItems(publishingHouses: List<PublishingHouse>) {
    this.publishingHousesList.addAll(publishingHouses)
    this.notifyDataSetChanged()
  }

  fun clearItems() {
    this.publishingHousesList.clear()
  }

  inner class PublishingHouseViewHolder : BaseViewHolder, PublishingHousesListItemViewModel.PublishingHousesListItemViewModelListener {

    private lateinit var publishingHousesListItemViewModel: PublishingHousesListItemViewModel
    private val binding: ItemPublishingHousesListBinding

    constructor(binding: ItemPublishingHousesListBinding) : super(binding.root) {
      this.binding = binding
    }

    override fun onBind(position: Int) {
      val publishingHouse = publishingHousesList[position]
      this.publishingHousesListItemViewModel = PublishingHousesListItemViewModel(publishingHouse, this)
      this.binding.viewModel = this.publishingHousesListItemViewModel
      this.binding.executePendingBindings()
    }

    override fun onDeleteClick(publishingHouseId: Long) {
      listener.onDeleteClick(publishingHouseId)
    }
  }

  interface PublishingHousesListAdapterListener : EmptyItemViewModel.EmptyItemViewModelListener {
    fun onDeleteClick(publishingHouseId: Long)
  }
}
