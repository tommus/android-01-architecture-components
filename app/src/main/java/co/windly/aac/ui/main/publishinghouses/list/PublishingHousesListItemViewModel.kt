package co.windly.aac.ui.main.publishinghouses.list

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse

class PublishingHousesListItemViewModel {

  private val publishingHouse: PublishingHouse
  private val listener: PublishingHousesListItemViewModelListener
  private val name: ObservableField<String>
  private val isDeleting = ObservableBoolean(false)

  constructor(publishingHouse: PublishingHouse, listener: PublishingHousesListItemViewModelListener) {
    this.publishingHouse = publishingHouse
    this.listener = listener
    this.name = ObservableField(publishingHouse.name)
  }

  fun onDeleteClick() {
    this.listener.onDeleteClick(this.publishingHouse.id)
    this.isDeleting.set(true)
  }

  fun getName() = this.name

  fun getIsDeleting() = this.isDeleting

  interface PublishingHousesListItemViewModelListener {
    fun onDeleteClick(publishingHouseId: Long)
  }
}
