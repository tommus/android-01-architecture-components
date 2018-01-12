package co.windly.aac.ui.main.publishinghouses.list

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import co.windly.aac.data.DataManager
import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.AacLogger
import co.windly.aac.utilities.rx.SchedulerProvider
import io.reactivex.rxkotlin.plusAssign

class PublishingHousesListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
  : BaseViewModel<PublishingHousesListNavigator>(dataManager, schedulerProvider) {

  private val publishingHousesObservableArrayList = ObservableArrayList<PublishingHouse>()
  private val publishingHousesListLiveData = MutableLiveData<MutableList<PublishingHouse>>()

  fun deletePublishingHouse(publishingHouseId: Long) {
    getCompositeDisposable() += getDataManager()
      .deletePublishingHouse(publishingHouseId)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .subscribe(
        {
          this.publishingHousesObservableArrayList.removeAll { it.id == publishingHouseId }
          this.publishingHousesListLiveData.value?.removeAll { it.id == publishingHouseId }
        },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun loadPublishingHouses() {
    this.setIsLoading(true)
    getCompositeDisposable() += getDataManager()
      .getPublishingHouses(active = true)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .doOnTerminate { this.setIsLoading(false) }
      .subscribe(
        { this.publishingHousesListLiveData.value = mutableListOf(*it.toTypedArray()) },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun getPublishingHousesListLiveData(): MutableLiveData<MutableList<PublishingHouse>>
    = this.publishingHousesListLiveData

  fun addPublishingHousesToList(publishingHouses: List<PublishingHouse>) {
    this.publishingHousesObservableArrayList.clear()
    this.publishingHousesObservableArrayList.addAll(publishingHouses)
  }

  fun getPublishingHousesObservableArrayList(): ObservableArrayList<PublishingHouse>
    = this.publishingHousesObservableArrayList
}
