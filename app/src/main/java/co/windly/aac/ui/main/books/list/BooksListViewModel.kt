package co.windly.aac.ui.main.books.list

import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableArrayList
import co.windly.aac.data.DataManager
import co.windly.aac.data.domain.models.books.Book
import co.windly.aac.ui.base.BaseViewModel
import co.windly.aac.utilities.AacLogger
import co.windly.aac.utilities.rx.SchedulerProvider
import io.reactivex.rxkotlin.plusAssign

class BooksListViewModel(dataManager: DataManager, schedulerProvider: SchedulerProvider)
  : BaseViewModel<BooksListNavigator>(dataManager, schedulerProvider) {

  private val booksObservableArrayList = ObservableArrayList<Book>()
  private val booksListLiveData = MutableLiveData<MutableList<Book>>()

  fun deleteBook(bookId: Long) {
    getCompositeDisposable() += getDataManager()
      .deleteBook(bookId)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .subscribe(
        {
          this.booksObservableArrayList.removeAll { it.id == bookId }
          this.booksListLiveData.value?.removeAll { it.id == bookId }
        },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun loadBooks() {
    this.setIsLoading(true)
    getCompositeDisposable() += getDataManager()
      .getBooks(active = true)
      .subscribeOn(getSchedulerProvider().io())
      .observeOn(getSchedulerProvider().ui())
      .doOnTerminate { this.setIsLoading(false) }
      .subscribe(
        { this.booksListLiveData.value = mutableListOf(*it.toTypedArray()) },
        { AacLogger.e(it.localizedMessage) }
      )
  }

  fun getBooksListLiveData(): MutableLiveData<MutableList<Book>>
    = this.booksListLiveData

  fun addBookItemsToList(books: List<Book>) {
    this.booksObservableArrayList.clear()
    this.booksObservableArrayList.addAll(books)
  }

  fun getBooksObservableArrayList(): ObservableArrayList<Book>
    = this.booksObservableArrayList
}
