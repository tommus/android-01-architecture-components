package co.windly.aac.ui.main.publishinghouses.list

import android.support.v7.widget.LinearLayoutManager
import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class PublishingHousesListModule {

  @Provides
  fun publishingHousesListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
    = PublishingHousesListViewModel(dataManager, schedulerProvider)

  @Provides
  fun providePublishingHousesListAdapter(): PublishingHousesListAdapter
    = PublishingHousesListAdapter(mutableListOf())

  @Provides
  fun provideLinearLayoutManager(fragment: PublishingHousesListFragment)
    = LinearLayoutManager(fragment.activity)
}
