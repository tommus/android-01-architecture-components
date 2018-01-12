package co.windly.aac.data.network.converters

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import org.joda.time.format.DateTimeFormatter

class DateTimeDeserializer : JsonDeserializer<DateTime>() {

  companion object {

    val FORMAT: DateTimeFormatter = DateTimeFormat.forPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSSZ")
  }

  override fun deserialize(parser: JsonParser, ctxt: DeserializationContext?): DateTime =
    DateTime.parse(parser.text.trim(), FORMAT)
}
