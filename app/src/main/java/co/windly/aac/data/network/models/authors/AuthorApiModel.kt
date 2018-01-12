package co.windly.aac.data.network.models.authors

import co.windly.aac.data.network.converters.DateTimeDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class AuthorApiModel(

  @get:JsonProperty("id")
  val id: Long = 0L,

  @get:JsonProperty("first_name")
  val firstName: String = StringUtils.EMPTY,

  @get:JsonProperty("last_name")
  val lastName: String = StringUtils.EMPTY,

  @get:JsonProperty("active")
  val active: Boolean = true,

  @get:JsonProperty("updated_at")
  @get:JsonDeserialize(using = DateTimeDeserializer::class)
  val updatedAt: DateTime = DateTime.now(),

  @get:JsonProperty("created_at")
  @get:JsonDeserialize(using = DateTimeDeserializer::class)
  val createdAt: DateTime = DateTime.now()
)
