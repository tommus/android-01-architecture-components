package co.windly.aac.ui.main.covers.list

import android.support.v7.widget.LinearLayoutManager
import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class CoversListModule {

  @Provides
  fun coversListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    = CoversListViewModel(dataManager, schedulerProvider)

  @Provides
  fun provideCoversListAdapter(): CoversListAdapter
    = CoversListAdapter(mutableListOf())

  @Provides
  fun provideLinearLayoutManager(fragment: CoversListFragment)
    = LinearLayoutManager(fragment.activity)
}
