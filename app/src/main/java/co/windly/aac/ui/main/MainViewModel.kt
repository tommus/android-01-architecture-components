package co.windly.aac.ui.main

import co.windly.aac.data.DataManager
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.rx.SchedulerProvider

class MainViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider) :
  BaseViewModel<MainNavigator>(dataManager, schedulerProvider)
