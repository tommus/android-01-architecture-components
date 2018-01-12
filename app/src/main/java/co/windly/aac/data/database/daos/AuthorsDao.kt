package co.windly.aac.data.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import co.windly.aac.data.database.models.authors.AuthorEntity
import co.windly.aac.data.database.models.authors.AuthorFirstName
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface AuthorsDao {

  //region Insert - Single

  @Insert /*(onConflict = OnConflictStrategy.ABORT) */
  fun insertAuthor(entity: AuthorEntity)

  @Insert(onConflict = OnConflictStrategy.ABORT)
  fun insertOrAbortAuthor(entity: AuthorEntity)

  @Insert(onConflict = OnConflictStrategy.FAIL)
  fun insertOrFailAuthor(entity: AuthorEntity)

  @Insert(onConflict = OnConflictStrategy.ROLLBACK)
  fun insertOrRollbackAuthor(entity: AuthorEntity)

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  fun insertOrIgnoreAuthor(entity: AuthorEntity)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun insertOrReplaceAuthor(entity: AuthorEntity)

  @Insert
  fun insertAuthorGetId(entity: AuthorEntity): Long

  //endregion

  //region Insert - Multiple

  @Insert
  fun insertTwoAuthors(first: AuthorEntity, second: AuthorEntity)

  @Insert
  fun insertAuthors(first: AuthorEntity, vararg others: AuthorEntity)

  @Insert
  fun insertAuthorsWithArray(first: AuthorEntity, entites: Array<AuthorEntity>)

  @Insert
  fun insertAuthorsWithList(first: AuthorEntity, entities: List<AuthorEntity>)

  @Insert
  fun insertAuthors(vararg entities: AuthorEntity)

  @Insert
  fun insertArrayOfAuthors(entities: Array<AuthorEntity>)

  @Insert
  fun insertListOfAuthors(entites: List<AuthorEntity>)

  @Insert
  fun insertListOfAuthorsGetIds(entites: List<AuthorEntity>): List<Long>

  //endregion

  //region Update

  @Update /* (onConflict = ...) */
  fun updateAuthor(entity: AuthorEntity)

  @Update
  fun updateAuthors(vararg entities: AuthorEntity)

  @Update
  fun updateArrayOfAuthors(entities: Array<AuthorEntity>)

  @Update
  fun updateListOfAuthors(entities: List<AuthorEntity>)

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  fun updateAuthorUsingInsertReplace(entity: AuthorEntity): Long

  //endregion

  //region Update - Partial

  @Query("UPDATE authors SET first_name = :firstName WHERE id = :authorId")
  fun updateFirstNameForAuthorWithId(authorId: Long, firstName: String)

  @Query("UPDATE authors SET last_name = :lastName WHERE id = :authorId")
  fun updateLastNameForAuthorWithId(authorId: Long, lastName: String)

  //endregion

  //region Delete - PK

  @Query("DELETE FROM authors")
  fun deleteAllAuthors() /* aka clearAuthors() */

  @Delete
  fun deleteAuthor(entity: AuthorEntity)

  @Delete
  fun deleteAuthors(vararg entity: AuthorEntity)

  @Delete
  fun deleteArrayOfAuthors(entities: Array<AuthorEntity>)

  @Delete
  fun deleteListOfAuthors(entities: List<AuthorEntity>)

  //endregion

  //region Delete - Argument

  @Query("DELETE FROM authors WHERE first_name = :firstName")
  fun deleteAuthorsWithFirstName(firstName: String)

  @Query("DELETE FROM authors WHERE last_name = :lastName")
  fun deleteAuthorsWithLastName(lastName: String)

  @Query("DELETE FROM authors WHERE first_name = :firstName AND last_name = :lastName")
  fun deleteAuthorsWithName(firstName: String, lastName: String)

  @Query("DELETE FROM authors WHERE id = :authorId")
  fun deleteAuthorWithId(authorId: Long)

  // Above is equivalent to:
  // @Delete
  // fun deleteAuthor(entity: AuthorEntity)
  // if 'id' is @PrimaryKey.

  //endregion

  //region Get - Regular

  @Query("SELECT * FROM authors")
  fun getListOfAllAuthors(): List<AuthorEntity>

  @Query("SELECT * FROM authors")
  fun getArrayOfAllAuthors(): Array<AuthorEntity>

  @Query("SELECT * FROM authors WHERE first_name = :firstName")
  fun getAuthorsWithFirstName(firstName: String): List<AuthorEntity>

  @Query("SELECT * FROM authors WHERE last_name = :lastName")
  fun getAuthorsWithLastName(lastName: String): List<AuthorEntity>

  @Query("SELECT * FROM authors WHERE first_name = :firstName AND last_name = :lastName")
  fun getAuthorsWithName(firstName: String, lastName: String): List<AuthorEntity>

  //endregion

  //region Get - Reactive

  /**
   * No entities = will onComplete.
   * Entity exist = emit onNext and then onComplete.
   * Entity update = if already onComplete nothing will happen.
   */
  @Query("SELECT * FROM authors")
  fun getRxMaybeListOfAllAuthors(): Maybe<List<AuthorEntity>>

  /**
   * No entities = will onError.
   * Entity exist = emit onSuccess.
   * Entity update = if already onSuccess nothing will happen.
   */
  @Query("SELECT * FROM authors")
  fun getRxSingleListOfAllAuthors(): Single<List<AuthorEntity>>

  /**
   * No entities = no emits (neither onNext or onError).
   * Entity exist = emit onNext.
   * Entity update = consecutive onNext emission.
   */
  @Query("SELECT * FROM authors")
  fun getRxFlowableListOfAllAuthors(): Flowable<List<AuthorEntity>>

  //endregion

  //region Get - Live Data

  @Query("SELECT * FROM authors")
  fun getLiveDataListOfAllAuthors(): LiveData<List<AuthorEntity>>

  @Query("SELECT * FROM authors")
  fun getLiveDataArrayOfAllUsers(): LiveData<Array<AuthorEntity>>

  //endregion

  //region Get - With projection

  @Query("SELECT first_name FROM authors")
  fun getFirstNamesOfAllAuthors(): List<String>

  @Query("SELECT last_name FROM authors")
  fun getLastNamesOfAllAuthors(): List<String>

  @Query("SELECT id FROM authors")
  fun getIdsOfAllAuthors(): List<Long>

  @Query("SELECT id, first_name FROM authors")
  fun getAllAuthorsAsSimpleAuthorProjection(): List<AuthorFirstName>

  //endregion

  //region Various

  @Query("SELECT Count(*) FROM authors")
  fun getAllAuthorsCount(): Long

  @Query("SELECT Count(*) FROM authors WHERE first_name = :firstName")
  fun getCountOfAuthorsWithFirstName(firstName: String): Long

  @Query("SELECT Count(*) FROM authors WHERE last_name = :lastName")
  fun getCountOfAuthorsWithLastName(lastName: String): Long

  //endregion
}
