package co.windly.aac.data.network.managers.publishinghouses

import co.windly.aac.data.domain.models.publishinghouses.PublishingHouse
import co.windly.aac.data.network.services.PublishingHousesService
import io.reactivex.Observable
import org.modelmapper.ModelMapper
import javax.inject.Inject

@Suppress("MemberVisibilityCanPrivate")
class PublishingHousesNetworkManager @Inject constructor() {

  @Inject lateinit var service: PublishingHousesService
  @Inject lateinit var mapper: ModelMapper

  fun getPublishingHouses(active: Boolean? = true): Observable<List<PublishingHouse>> {
    return this.service.getPublishingHouses(active)
      .flatMapIterable { it }
      .map { this.mapper.map(it, PublishingHouse::class.java) }
      .toList()
      .toObservable()
  }

  fun deletePublishingHouse(publishingHouseId: Long): Observable<Boolean> =
    this.service.deletePublishingHouse(publishingHouseId).map { it.isSuccessful }
}
