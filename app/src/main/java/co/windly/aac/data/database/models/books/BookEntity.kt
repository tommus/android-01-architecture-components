package co.windly.aac.data.database.models.books

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey
import co.windly.aac.data.database.models.authors.AuthorEntity
import co.windly.aac.data.database.models.covers.CoverEntity
import co.windly.aac.data.database.models.publishinghouses.PublishingHouseEntity
import org.apache.commons.lang3.StringUtils.EMPTY

@Entity(tableName = "books")
data class BookEntity(

  @ColumnInfo(name = "id")
  @PrimaryKey(autoGenerate = false)
  var id: Long,

  @ColumnInfo(name = "title")
  var title: String = EMPTY,

  @ColumnInfo(name = "pages")
  var pages: Int = 0,

  @ColumnInfo(name = "description")
  var description: String = EMPTY,

  @ColumnInfo(name = "isbn")
  var isbn: String = EMPTY,

  @ColumnInfo(name = "quantity")
  var quantity: Int = 0,

  @ColumnInfo(name = "active")
  var acrtive: Boolean = true,

  @ColumnInfo(name = "updated_at")
  var updatedAt: String = EMPTY,

  @ColumnInfo(name = "created_at")
  var CreatedAt: String = EMPTY,

  @ColumnInfo(name = "cover_id")
  @ForeignKey(
    entity = CoverEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("cover_id")
  )
  var coverId: Long = 0L,

  @ColumnInfo(name = "author_id")
  @ForeignKey(
    entity = AuthorEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("author_id")
  )
  var authorId: Long = 0L,

  @ColumnInfo(name = "publishing_house_id")
  @ForeignKey(
    entity = PublishingHouseEntity::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("publishing_house_id")
  )
  var publishingHouseId: Long = 0L
)
