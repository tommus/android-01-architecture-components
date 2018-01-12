package co.windly.aac.data.network.services

import co.windly.aac.data.network.models.covers.CoverApiModel
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoversService {

  @GET(value = "library/covers/")
  fun getCovers(@Query("active") active: Boolean? = null): Observable<List<CoverApiModel>>

  @DELETE(value = "library/covers/{pk}/")
  fun deleteCover(@Path("pk") coverId: Long): Observable<Response<Void>>
}
