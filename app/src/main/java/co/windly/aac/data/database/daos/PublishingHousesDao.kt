package co.windly.aac.data.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import co.windly.aac.data.database.models.publishinghouses.PublishingHouseEntity

@Dao
interface PublishingHousesDao {

  //region Get

  @Query("SELECT * FROM publishing_houses")
  fun getListOfAllPublishingHouses(): List<PublishingHouseEntity>

  //endregion
}