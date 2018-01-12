package co.windly.aac.data.domain.models.publishinghouses

import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

class PublishingHouse(
  var id: Long = 0L,
  var name: String = StringUtils.EMPTY,
  var active: Boolean = true,
  var updatedAt: DateTime = DateTime.now(),
  var createdAt: DateTime = DateTime.now()
)
