package co.windly.aac.data.database.models.publishinghouses

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import org.apache.commons.lang3.StringUtils.EMPTY

@Entity(tableName = "publishing_houses")
data class PublishingHouseEntity(

  @ColumnInfo(name = "id")
  @PrimaryKey(autoGenerate = false)
  var id: Long,

  @ColumnInfo(name = "name")
  var name: String = EMPTY,

  @ColumnInfo(name = "active")
  var acrtive: Boolean = true,

  @ColumnInfo(name = "updated_at")
  var updatedAt: String = EMPTY,

  @ColumnInfo(name = "created_at")
  var CreatedAt: String = EMPTY
)
