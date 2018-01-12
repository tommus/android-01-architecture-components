package co.windly.aac.ui.main.covers.list

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import co.windly.aac.data.DataManager
import co.windly.aac.data.domain.models.covers.Cover
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.AacLogger
import co.windly.aac.utilities.rx.SchedulerProvider
import io.reactivex.rxkotlin.plusAssign

class CoversListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
  : BaseViewModel<CoversListNavigator>(dataManager, schedulerProvider) {

  private val coversObservableArrayList = ObservableArrayList<Cover>()
  private val coversListLiveData = MutableLiveData<MutableList<Cover>>()

  fun deleteCover(coverId: Long) {
    getCompositeDisposable() += getDataManager()
      .deleteCover(coverId)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .subscribe(
        {
          this.coversObservableArrayList.removeAll { it.id == coverId }
          this.coversListLiveData.value?.removeAll { it.id == coverId }
        },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun loadCovers() {
    this.setIsLoading(true)
    getCompositeDisposable() += getDataManager()
      .getCovers(active = true)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .doOnTerminate { this.setIsLoading(false) }
      .subscribe(
        { this.coversListLiveData.value = mutableListOf(*it.toTypedArray()) },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun getCoversListLiveData(): MutableLiveData<MutableList<Cover>>
    = this.coversListLiveData

  fun addCoverItemsToList(covers: List<Cover>) {
    this.coversObservableArrayList.clear()
    this.coversObservableArrayList.addAll(covers)
  }

  fun getCoversObservableArrayList(): ObservableArrayList<Cover>
    = this.coversObservableArrayList
}
