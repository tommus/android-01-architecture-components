package co.windly.aac.ui.main

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import co.windly.aac.BR
import co.windly.aac.R
import co.windly.aac.databinding.ActivityMainBinding
import co.windly.aac.ui.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity<ActivityMainBinding, MainViewModel>(), HasSupportFragmentInjector {

  @Inject
  lateinit var mainViewModel: MainViewModel

  @Inject
  lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

  private lateinit var activityMainBinding: ActivityMainBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    this.activityMainBinding = this.getViewDataBinding()

    this.navigation.setOnNavigationItemSelectedListener(this.navigationListener)
    this.navigation.selectedItemId = R.id.navigation_authors
  }

  override fun getViewModel(): MainViewModel
    = this.mainViewModel

  override fun getBindingVariable(): Int
    = BR.viewModel

  override fun getLayoutId(): Int
    = R.layout.activity_main

  override fun supportFragmentInjector(): AndroidInjector<Fragment>
    = this.fragmentDispatchingAndroidInjector

  private val navigationListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
    when (item.itemId) {
      R.id.navigation_authors -> {
        this.mainViewModel.getNavigator().showAuthorsListView()
        true
      }
      R.id.navigation_publishing_houses -> {
        this.mainViewModel.getNavigator().showBooksListView()
        true
      }
      R.id.navigation_covers -> {
        this.mainViewModel.getNavigator().showCoversListView()
        true
      }
      R.id.navigation_books -> {
        this.mainViewModel.getNavigator().showPublishingHousesListView()
        true
      }
      else -> false
    }
  }
}
