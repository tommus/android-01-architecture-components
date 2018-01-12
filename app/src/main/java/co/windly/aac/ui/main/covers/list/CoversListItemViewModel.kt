package co.windly.aac.ui.main.covers.list

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import co.windly.aac.data.domain.models.covers.Cover

class CoversListItemViewModel {

  private val cover: Cover
  private val listener: CoversListItemViewModelListener
  private val name: ObservableField<String>
  private val isDeleting = ObservableBoolean(false)

  constructor(cover: Cover, listener: CoversListItemViewModelListener) {
    this.cover = cover
    this.listener = listener
    this.name = ObservableField(cover.name)
  }

  fun onDeleteClick() {
    this.listener.onDeleteClick(this.cover.id)
    this.isDeleting.set(true)
  }

  fun getName() = this.name

  fun getIsDeleting() = this.isDeleting

  interface CoversListItemViewModelListener {
    fun onDeleteClick(coverId: Long)
  }
}
