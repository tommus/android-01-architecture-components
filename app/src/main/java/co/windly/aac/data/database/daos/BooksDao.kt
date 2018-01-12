package co.windly.aac.data.database.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Query
import co.windly.aac.data.database.models.books.BookEntity

@Dao
interface BooksDao {

  //region Get

  @Query("SELECT * FROM books")
  fun getListOfAllBooks(): List<BookEntity>

  //endregion
}