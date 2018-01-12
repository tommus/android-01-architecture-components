package co.windly.aac.ui.main.books.list

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import co.windly.aac.data.domain.models.books.Book
import co.windly.aac.databinding.ItemBooksListBinding
import co.windly.aac.databinding.ItemEmptyListBinding
import co.windly.aac.ui.base.BaseViewHolder
import co.windly.aac.ui.main.empty.EmptyItemViewHolder
import co.windly.aac.ui.main.empty.EmptyItemViewModel

class BooksListAdapter : RecyclerView.Adapter<BaseViewHolder> {

  companion object {
    const val VIEW_TYPE_EMPTY = 0
    const val VIEW_TYPE_NORMAL = 1
  }

  private val booksList: MutableList<Book>
  private lateinit var listener: BooksListAdapterListener

  constructor(booksList: List<Book>) {
    this.booksList = mutableListOf(*booksList.toTypedArray())
  }

  override fun onBindViewHolder(holder: BaseViewHolder?, position: Int) {
    holder?.onBind(position)
  }

  override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): BaseViewHolder {
    val inflater = LayoutInflater.from(parent?.context)

    return when (viewType) {
      VIEW_TYPE_NORMAL -> {
        val binding = ItemBooksListBinding.inflate(inflater, parent, false)
        BookViewHolder(binding)
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
    = if (this.booksList.isNotEmpty()) {
    VIEW_TYPE_NORMAL
  } else {
    VIEW_TYPE_EMPTY
  }

  override fun getItemCount(): Int
    = if (this.booksList.isNotEmpty()) {
    this.booksList.size
  } else {
    1
  }

  fun setListener(listener: BooksListAdapterListener) {
    this.listener = listener
  }

  fun addItems(books: List<Book>) {
    this.booksList.addAll(books)
    this.notifyDataSetChanged()
  }

  fun clearItems() {
    this.booksList.clear()
  }

  inner class BookViewHolder : BaseViewHolder, BooksListItemViewModel.BooksListItemViewModelListener {

    private lateinit var booksListItemViewModel: BooksListItemViewModel
    private val binding: ItemBooksListBinding

    constructor(binding: ItemBooksListBinding) : super(binding.root) {
      this.binding = binding
    }

    override fun onBind(position: Int) {
      val book = booksList[position]
      this.booksListItemViewModel = BooksListItemViewModel(book, this)
      this.binding.viewModel = this.booksListItemViewModel
      this.binding.executePendingBindings()
    }

    override fun onDeleteClick(bookId: Long) {
      listener.onDeleteClick(bookId)
    }
  }

  interface BooksListAdapterListener : EmptyItemViewModel.EmptyItemViewModelListener {
    fun onDeleteClick(bookId: Long)
  }
}
