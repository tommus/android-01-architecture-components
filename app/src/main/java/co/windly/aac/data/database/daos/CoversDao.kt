package co.windly.aac.data.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import co.windly.aac.data.database.models.covers.CoverEntity

@Dao
interface CoversDao {

  //region Get

  @Query("SELECT * FROM covers")
  fun getListOfAllCovers(): List<CoverEntity>

  //endregion
}