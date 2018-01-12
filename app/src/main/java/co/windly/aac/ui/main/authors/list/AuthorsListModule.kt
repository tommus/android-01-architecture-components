package co.windly.aac.ui.main.authors.list

import android.support.v7.widget.LinearLayoutManager
import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class AuthorsListModule {

  @Provides
  fun authorsListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider): AuthorsListViewModel
    = AuthorsListViewModel(dataManager, schedulerProvider)

  @Provides
  fun provideAuthorsListAdapter(): AuthorsListAdapter
    = AuthorsListAdapter(mutableListOf())

  @Provides
  fun provideLinearLayoutManager(fragment: AuthorsListFragment): LinearLayoutManager
    = LinearLayoutManager(fragment.activity)
}
