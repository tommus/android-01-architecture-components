package co.windly.aac.ui.splash

import co.windly.aac.data.DataManager
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.AacLogger
import co.windly.aac.utilities.rx.SchedulerProvider
import io.reactivex.rxkotlin.plusAssign

class SplashViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
  : BaseViewModel<SplashNavigator>(dataManager, schedulerProvider) {

  fun initialize() {
    AacLogger.d("Initialize.")
    getCompositeDisposable() += getDataManager()
      .initialize()
      .subscribeOn(getSchedulerProvider().computation())
      .observeOn(getSchedulerProvider().ui())
      .subscribe(
        { this.getNavigator().showMainView() },
        { AacLogger.e(it.localizedMessage) }
      )
  }
}
