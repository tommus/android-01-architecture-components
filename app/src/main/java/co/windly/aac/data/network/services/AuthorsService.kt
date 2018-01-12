package co.windly.aac.data.network.services

import co.windly.aac.data.network.models.authors.AuthorApiModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AuthorsService {

  @GET(value = "library/authors/")
  fun getAuthors(@Query("active") active: Boolean? = null): Observable<List<AuthorApiModel>>

  @DELETE(value = "library/authors/{pk}/")
  fun deleteAuthor(@Path("pk") authorId: Long): Observable<Response<Void>>
}
