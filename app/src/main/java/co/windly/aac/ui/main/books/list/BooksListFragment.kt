package co.windly.aac.ui.main.books.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.windly.aac.BR
import co.windly.aac.R
import co.windly.aac.data.domain.models.books.Book
import co.windly.aac.databinding.FragmentMainBooksListBinding
import co.windly.aac.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_authors_list.*
import javax.inject.Inject

class BooksListFragment : BaseFragment<FragmentMainBooksListBinding, BooksListViewModel>(), BooksListNavigator,
  BooksListAdapter.BooksListAdapterListener {

  @Inject
  lateinit var booksListAdapter: BooksListAdapter

  @Inject
  lateinit var layoutManager: LinearLayoutManager

  private lateinit var fragmentMainBooksListBinding: FragmentMainBooksListBinding

  @Inject
  lateinit var booksListViewModel: BooksListViewModel

  companion object {
    fun newInstance() = BooksListFragment().apply { this.arguments = Bundle() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.booksListAdapter.setListener(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    this.fragmentMainBooksListBinding = this.getViewDataBinding()
    setUp()
    subscribeToLiveData()
    getViewModel().loadBooks()
  }

  override fun getViewModel(): BooksListViewModel
    = this.booksListViewModel

  override fun getBindingVariable(): Int
    = BR.viewModel

  override fun getLayoutId(): Int
    = R.layout.fragment_main_books_list

  override fun onDeleteClick(bookId: Long) {
    getViewModel().deleteBook(bookId)
  }

  override fun onRetryClick() {
    getViewModel().loadBooks()
  }

  private fun setUp() {
    this.layoutManager.orientation = LinearLayoutManager.VERTICAL
    this.fragmentMainBooksListBinding.recyclerView.layoutManager = this.layoutManager
    this.fragmentMainBooksListBinding.recyclerView.itemAnimator = DefaultItemAnimator()
    this.fragmentMainBooksListBinding.recyclerView.adapter = this.booksListAdapter
    this.swipeRefresh.setOnRefreshListener { getViewModel().loadBooks() }
  }

  private fun subscribeToLiveData() {
    getViewModel().getBooksListLiveData().observe(this, Observer<MutableList<Book>> { books ->
      books?.let { getViewModel().addBookItemsToList(it) }
    })
  }
}
