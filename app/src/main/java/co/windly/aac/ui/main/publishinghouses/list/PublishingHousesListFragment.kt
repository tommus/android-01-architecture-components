package co.windly.aac.ui.main.publishinghouses.list

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import co.windly.aac.R
import co.windly.aac.databinding.FragmentMainPublishingHousesListBinding
import co.windly.aac.ui.base.BaseFragment
import com.android.databinding.library.baseAdapters.BR
import kotlinx.android.synthetic.main.fragment_main_authors_list.*
import javax.inject.Inject

class PublishingHousesListFragment : BaseFragment<FragmentMainPublishingHousesListBinding, PublishingHousesListViewModel>(), PublishingHousesListNavigator,
  PublishingHousesListAdapter.PublishingHousesListAdapterListener {

  @Inject
  lateinit var publishingHousesListAdapter: PublishingHousesListAdapter

  @Inject
  lateinit var layoutManager: LinearLayoutManager

  private lateinit var fragmentMainPublishingHousesListBinding: FragmentMainPublishingHousesListBinding

  @Inject
  lateinit var publishingHousesListViewModel: PublishingHousesListViewModel

  companion object {
    fun newInstance() = PublishingHousesListFragment().apply { this.arguments = Bundle() }
  }

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.publishingHousesListAdapter.setListener(this)
  }

  override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    this.fragmentMainPublishingHousesListBinding = this.getViewDataBinding()
    setUp()
    subscribeToLiveData()
    getViewModel().loadPublishingHouses()
  }

  override fun getViewModel(): PublishingHousesListViewModel
    = this.publishingHousesListViewModel

  override fun getBindingVariable(): Int
    = BR.viewModel

  override fun getLayoutId(): Int
    = R.layout.fragment_main_publishing_houses_list

  override fun onDeleteClick(publishingHouseId: Long) {
    getViewModel().deletePublishingHouse(publishingHouseId)
  }

  override fun onRetryClick() {
    getViewModel().loadPublishingHouses()
  }

  private fun setUp() {
    this.layoutManager.orientation = LinearLayoutManager.VERTICAL
    this.fragmentMainPublishingHousesListBinding.recyclerView.layoutManager = this.layoutManager
    this.fragmentMainPublishingHousesListBinding.recyclerView.itemAnimator = DefaultItemAnimator()
    this.fragmentMainPublishingHousesListBinding.recyclerView.adapter = this.publishingHousesListAdapter
    this.swipeRefresh.setOnRefreshListener { getViewModel().loadPublishingHouses() }
  }

  private fun subscribeToLiveData() {
    getViewModel().getPublishingHousesListLiveData().observe(this, Observer { publishingHouses ->
      publishingHouses?.let { getViewModel().addPublishingHousesToList(it) }
    })
  }
}
