package co.windly.aac.data

import co.windly.aac.data.domain.models.authors.Author
import co.windly.aac.data.domain.models.books.Book
import co.windly.aac.data.domain.models.covers.Cover
import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse
import io.reactivex.Completable
import io.reactivex.Observable

interface DataManager {

  fun initialize(): Completable

  fun getAuthors(active: Boolean? = null): Observable<List<Author>>

  fun deleteAuthor(authorId: Long): Observable<Boolean>

  fun getBooks(active: Boolean? = null): Observable<List<Book>>

  fun deleteBook(bookId: Long): Observable<Boolean>

  fun getCovers(active: Boolean? = null): Observable<List<Cover>>

  fun deleteCover(coverId: Long): Observable<Boolean>

  fun getPublishingHouses(active: Boolean? = null): Observable<List<PublishingHouse>>

  fun deletePublishingHouse(publishingHouseId: Long): Observable<Boolean>
}
