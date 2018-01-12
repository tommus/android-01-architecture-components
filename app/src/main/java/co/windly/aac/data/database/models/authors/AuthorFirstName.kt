package co.windly.aac.data.database.models.authors

import android.arch.persistence.room.ColumnInfo
import org.apache.commons.lang3.StringUtils.EMPTY

data class AuthorFirstName(

  @ColumnInfo(name = "id")
  var id: Long,

  @ColumnInfo(name = "first_name")
  var firstName: String = EMPTY
)
