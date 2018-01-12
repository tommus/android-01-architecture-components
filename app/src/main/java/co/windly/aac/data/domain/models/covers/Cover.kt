package co.windly.aac.data.domain.models.covers

import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

data class Cover(
  var id: Long = 0L,
  var name: String = StringUtils.EMPTY,
  var active: Boolean = true,
  var updatedAt: DateTime = DateTime.now(),
  var createdAt: DateTime = DateTime.now()
)
