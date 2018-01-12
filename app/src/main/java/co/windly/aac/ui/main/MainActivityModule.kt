package co.windly.aac.ui.main

import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule {

  @Provides
  fun provideMainNavigator(mainActivity: MainActivity): MainNavigator
    = MainNavigatorController(mainActivity)

  @Provides
  fun provideMainViewModel(
    dataManager: DataManager, schedulerProvider: SchedulerProvider, mainNavigator: MainNavigator): MainViewModel
    = MainViewModel(dataManager, schedulerProvider).apply { this.setNavigator(mainNavigator) }
}
