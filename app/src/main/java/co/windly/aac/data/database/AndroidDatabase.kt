package co.windly.aac.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import co.windly.aac.data.database.converters.DateTimeConverter
import co.windly.aac.data.database.daos.*
import co.windly.aac.data.database.models.authors.AuthorEntity
import co.windly.aac.data.database.models.books.BookEntity
import co.windly.aac.data.database.models.covers.CoverEntity
import co.windly.aac.data.database.models.publishinghouses.PublishingHouseEntity

@Database(
  version = 1,
  exportSchema = false,
  entities = [
    AuthorEntity::class,
    BookEntity::class,
    CoverEntity::class,
    PublishingHouseEntity::class
  ]
)
@TypeConverters(
  value = [
    DateTimeConverter::class
  ]
)
abstract class AndroidDatabase : RoomDatabase() {

  companion object {
    val DATABASE_NAME = "aac_database.sqlite3"
  }

  abstract fun allDao(): AllDao

  abstract fun authorsDao(): AuthorsDao

  abstract fun booksDao(): BooksDao

  abstract fun coversDao(): CoversDao

  abstract fun publishingHousesDao(): PublishingHousesDao
}
