package co.windly.aac.ui.main.books.list

import android.support.v7.widget.LinearLayoutManager
import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class BooksListModule {

  @Provides
  fun booksListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    = BooksListViewModel(dataManager, schedulerProvider)

  @Provides
  fun provideBooksListAdapter(): BooksListAdapter
    = BooksListAdapter(mutableListOf())

  @Provides
  fun provideLinearLayoutManager(fragment: BooksListFragment): LinearLayoutManager
    = LinearLayoutManager(fragment.activity)
}
