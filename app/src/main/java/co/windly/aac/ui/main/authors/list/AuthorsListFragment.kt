package co.windly.aac.ui.main.authors.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.windly.aac.BR
import co.windly.aac.R
import co.windly.aac.data.domain.models.authors.Author
import co.windly.aac.databinding.FragmentMainAuthorsListBinding
import co.windly.aac.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_main_authors_list.*
import javax.inject.Inject

class AuthorsListFragment : BaseFragment<FragmentMainAuthorsListBinding, AuthorsListViewModel>(), AuthorsListNavigator,
  AuthorsListAdapter.AuthorsListAdapterListener {

  @Inject
  lateinit var authorsListAdapter: AuthorsListAdapter

  @Inject
  lateinit var layoutManager: LinearLayoutManager

  private lateinit var fragmentMainAuthorsListBinding: FragmentMainAuthorsListBinding

  @Inject
  lateinit var authorsListViewModel: AuthorsListViewModel

  companion object {
    fun newInstance() = AuthorsListFragment().apply { this.arguments = Bundle() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.authorsListAdapter.setListener(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    this.fragmentMainAuthorsListBinding = this.getViewDataBinding()
    setUp()
    subscribeToLiveData()
    getViewModel().loadAuthors()
  }

  override fun getViewModel(): AuthorsListViewModel
    = this.authorsListViewModel

  override fun getBindingVariable(): Int
    = BR.viewModel

  override fun getLayoutId(): Int
    = R.layout.fragment_main_authors_list

  override fun onDeleteClick(authorId: Long) {
    getViewModel().deleteAuthor(authorId)
  }

  override fun onRetryClick() {
    getViewModel().loadAuthors()
  }

  private fun setUp() {
    this.layoutManager.orientation = LinearLayoutManager.VERTICAL
    this.fragmentMainAuthorsListBinding.recyclerView.layoutManager = this.layoutManager
    this.fragmentMainAuthorsListBinding.recyclerView.itemAnimator = DefaultItemAnimator()
    this.fragmentMainAuthorsListBinding.recyclerView.adapter = this.authorsListAdapter
    this.swipeRefresh.setOnRefreshListener { getViewModel().loadAuthors() }
  }

  private fun subscribeToLiveData() {
    getViewModel().getAuthorsListLiveData().observe(this, Observer<MutableList<Author>> { authors ->
      authors?.let { getViewModel().addAuthorItemsToList(it) }
    })
  }
}
