package co.windly.aac.data.domain.models.authors

import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

data class Author(
  var id: Long = 0L,
  var firstName: String = StringUtils.EMPTY,
  var lastName: String = StringUtils.EMPTY,
  var active: Boolean = true,
  var updatedAt: DateTime = DateTime.now(),
  var createdAt: DateTime = DateTime.now()
)
