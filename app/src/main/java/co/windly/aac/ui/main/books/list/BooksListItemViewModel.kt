package co.windly.aac.ui.main.books.list

import android.databinding.ObservableBoolean
import android.databinding.ObservableField
import co.windly.aac.data.domain.models.books.Book

class BooksListItemViewModel {

  private val book: Book
  private val listener: BooksListItemViewModelListener
  private val title: ObservableField<String>
  private val isDeleting = ObservableBoolean(false)

  constructor(book: Book, listener: BooksListItemViewModelListener) {
    this.book = book
    this.listener = listener
    this.title = ObservableField(book.title)
  }

  fun onDeleteClick() {
    this.listener.onDeleteClick(this.book.id)
    this.isDeleting.set(true)
  }

  fun getTitle() = this.title

  fun getIsDeleting() = this.isDeleting

  interface BooksListItemViewModelListener {
    fun onDeleteClick(bookId: Long)
  }
}
