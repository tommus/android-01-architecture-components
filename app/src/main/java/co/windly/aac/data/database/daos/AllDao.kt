package co.windly.aac.data.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import android.arch.persistence.room.Transaction

@Dao
abstract class AllDao {

  @Query("DELETE FROM authors")
  abstract fun clearAuthors()

  @Query("DELETE FROM books")
  abstract fun clearBooks()

  @Query("DELETE FROM covers")
  abstract fun clearCovers()

  @Query("DELETE FROM publishing_houses")
  abstract fun clearPublishingHouses()

  @Transaction
  open fun clearDatabase() {
    this.clearAuthors()
    this.clearBooks()
    this.clearCovers()
    this.clearPublishingHouses()
  }
}
