package co.windly.aac.ui.main

import android.support.annotation.IdRes
import android.support.v4.app.FragmentManager
import co.windly.aac.R
import co.windly.aac.ui.main.authors.list.AuthorsListFragment
import co.windly.aac.ui.main.books.list.BooksListFragment
import co.windly.aac.ui.main.covers.list.CoversListFragment
import co.windly.aac.ui.main.publishinghouses.list.PublishingHousesListFragment
import javax.inject.Inject

class MainNavigatorController : MainNavigator {

  @IdRes
  private val containerId: Int
  private val fragmentManager: FragmentManager

  @Inject
  constructor(mainActivity: MainActivity) {
    this.fragmentManager = mainActivity.supportFragmentManager
    this.containerId = R.id.fragmentContainer
  }

  override fun showAuthorsListView() {
    val authorsFragment = AuthorsListFragment.newInstance()
    this.fragmentManager.beginTransaction()
      .replace(this.containerId, authorsFragment)
      .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_leave)
      .commitAllowingStateLoss()
  }

  override fun showBooksListView() {
    val booksFragment = BooksListFragment.newInstance()
    this.fragmentManager.beginTransaction()
      .replace(this.containerId, booksFragment)
      .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_leave)
      .commitAllowingStateLoss()
  }

  override fun showCoversListView() {
    val coversFragment = CoversListFragment.newInstance()
    this.fragmentManager.beginTransaction()
      .replace(this.containerId, coversFragment)
      .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_leave)
      .commitAllowingStateLoss()
  }

  override fun showPublishingHousesListView() {
    val housesFragment = PublishingHousesListFragment.newInstance()
    this.fragmentManager.beginTransaction()
      .replace(this.containerId, housesFragment)
      .setCustomAnimations(R.anim.fragment_enter, R.anim.fragment_leave)
      .commitAllowingStateLoss()
  }
}
