package co.windly.aac.ui.splash

import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class SplashActivityModule {

  @Provides
  fun provideSplashNavigator(splashActivity: SplashActivity): SplashNavigator
    = SplashNavigatorController(splashActivity)

  @Provides
  fun provideSplashViewModel(
    dataManager: DataManager, schedulerProvider: SchedulerProvider, splashNavigator: SplashNavigator): SplashViewModel
    = SplashViewModel(dataManager, schedulerProvider).apply { this.setNavigator(splashNavigator) }
}
