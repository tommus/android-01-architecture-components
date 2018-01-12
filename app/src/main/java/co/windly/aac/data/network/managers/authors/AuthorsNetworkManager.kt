package co.windly.aac.data.network.managers.authors

import co.windly.aac.data.domain.models.authors.Author
import co.windly.aac.data.network.services.AuthorsService
import io.reactivex.Observable
import org.modelmapper.ModelMapper
import javax.inject.Inject

@Suppress("MemberVisibilityCanPrivate")
class AuthorsNetworkManager @Inject constructor() {

  @Inject lateinit var service: AuthorsService
  @Inject lateinit var mapper: ModelMapper

  fun getAuthors(active: Boolean? = null): Observable<List<Author>> {
    return this.service.getAuthors(active)
      .flatMapIterable { it }
      .map { this.mapper.map(it, Author::class.java) }
      .toList()
      .toObservable()
  }

  fun deleteAuthor(authorId: Long): Observable<Boolean> =
    this.service.deleteAuthor(authorId).map { it.isSuccessful }
}
