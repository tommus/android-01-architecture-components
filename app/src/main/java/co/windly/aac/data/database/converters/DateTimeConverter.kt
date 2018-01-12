package co.windly.aac.data.database.converters

import android.arch.persistence.room.TypeConverter
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class DateTimeConverter {

  companion object {
    val FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")
  }

  @TypeConverter
  fun serialize(dateTime: DateTime): String =
    dateTime.toString(FORMAT)

  @TypeConverter
  fun deserialize(string: String): DateTime =
    DateTime.parse(string, FORMAT)
}
