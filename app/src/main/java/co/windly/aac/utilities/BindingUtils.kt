package co.windly.aac.utilities

import android.databinding.BindingAdapter
import android.support.v7.widget.RecyclerView
import android.view.View
import co.windly.aac.data.domain.models.authors.Author
import co.windly.aac.data.domain.models.books.Book
import co.windly.aac.data.domain.models.covers.Cover
import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse
import co.windly.aac.ui.main.authors.list.AuthorsListAdapter
import co.windly.aac.ui.main.books.list.BooksListAdapter
import co.windly.aac.ui.main.covers.list.CoversListAdapter
import co.windly.aac.ui.main.publishinghouses.list.PublishingHousesListAdapter

class BindingUtils private constructor() {

  companion object {

    @JvmStatic
    @BindingAdapter("adapter")
    fun addAuthorsListItems(recyclerView: RecyclerView, authors: ArrayList<Author>) {
      recyclerView.adapter?.let {
        val adapter = it as AuthorsListAdapter
        adapter.clearItems()
        adapter.addItems(authors)
      }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun addBooksListItems(recyclerView: RecyclerView, books: ArrayList<Book>) {
      recyclerView.adapter?.let {
        val adapter = it as BooksListAdapter
        adapter.clearItems()
        adapter.addItems(books)
      }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun addCoversListItems(recyclerView: RecyclerView, covers: ArrayList<Cover>) {
      recyclerView.adapter?.let {
        val adapter = it as CoversListAdapter
        adapter.clearItems()
        adapter.addItems(covers)
      }
    }

    @JvmStatic
    @BindingAdapter("adapter")
    fun addPublishingHousesListItems(recyclerView: RecyclerView, publishingHouses: ArrayList<PublishingHouse>) {
      recyclerView.adapter?.let {
        val adapter = it as PublishingHousesListAdapter
        adapter.clearItems()
        adapter.addItems(publishingHouses)
      }
    }

    @JvmStatic
    @BindingAdapter("android:visibility")
    fun setVisibility(view: View, visible: Boolean) {
      view.visibility = if (visible) View.VISIBLE else View.INVISIBLE
    }
  }
}
