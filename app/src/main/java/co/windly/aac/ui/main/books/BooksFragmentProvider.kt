package co.windly.aac.ui.main.books

import co.windly.aac.ui.main.books.list.BooksListFragment
import co.windly.aac.ui.main.books.list.BooksListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class BooksFragmentProvider {

  @ContributesAndroidInjector(modules = [BooksListModule::class])
  abstract fun provideBooksListFragmentFactory(): BooksListFragment
}
