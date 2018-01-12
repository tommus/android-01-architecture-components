package co.windly.aac.ui.main.authors.list

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import co.windly.aac.data.DataManager
import co.windly.aac.data.domain.models.authors.Author
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.AacLogger
import co.windly.aac.utilities.rx.SchedulerProvider
import io.reactivex.rxkotlin.plusAssign

class AuthorsListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
  : BaseViewModel<AuthorsListNavigator>(dataManager, schedulerProvider) {

  private val authorsObservableArrayList = ObservableArrayList<Author>()
  private val authorsListLiveData = MutableLiveData<MutableList<Author>>()

  fun deleteAuthor(authorId: Long) {
    getCompositeDisposable() += getDataManager()
      .deleteAuthor(authorId)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .subscribe(
        {
          this.authorsObservableArrayList.removeAll { it.id == authorId }
          this.authorsListLiveData.value?.removeAll { it.id == authorId }
        },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun loadAuthors() {
    this.setIsLoading(true)
    getCompositeDisposable() += getDataManager()
      .getAuthors(active = true)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .doOnTerminate { this.setIsLoading(false) }
      .subscribe(
        { this.authorsListLiveData.value = mutableListOf(*it.toTypedArray()) },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun getAuthorsListLiveData(): MutableLiveData<MutableList<Author>>
    = this.authorsListLiveData

  fun addAuthorItemsToList(authors: List<Author>) {
    this.authorsObservableArrayList.clear()
    this.authorsObservableArrayList.addAll(authors)
  }

  fun getAuthorsObservableArrayList(): ObservableArrayList<Author>
    = this.authorsObservableArrayList
}
