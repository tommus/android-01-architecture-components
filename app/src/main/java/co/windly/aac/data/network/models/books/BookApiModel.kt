package co.windly.aac.data.network.models.books

import co.windly.aac.data.network.converters.DateTimeDeserializer
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import org.apache.commons.lang3.StringUtils
import org.joda.time.DateTime

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
data class BookApiModel(

  @get:JsonProperty("id")
  val id: Long = 0L,

  @get:JsonProperty("title")
  val title: String = StringUtils.EMPTY,

  @get:JsonProperty("pages")
  val pages: Int = 0,

  @get:JsonProperty("description")
  val description: String = StringUtils.EMPTY,

  @get:JsonProperty("isbn")
  val isbn: String = StringUtils.EMPTY,

  @get:JsonProperty("quantity")
  val quantity: Int = 0,

  @get:JsonProperty("active")
  val active: Boolean = true,

  @get:JsonProperty("updated_at")
  @get:JsonDeserialize(using = DateTimeDeserializer::class)
  val updatedAt: DateTime = DateTime.now(),

  @get:JsonProperty("created_at")
  @get:JsonDeserialize(using = DateTimeDeserializer::class)
  val createdAt: DateTime = DateTime.now(),

  @get:JsonProperty("cover")
  val coverId: Long = 0L,

  @get:JsonProperty("author")
  val authorId: Long = 0L,

  @get:JsonProperty("publishing_house")
  val publishingHouseId: Long = 0L
)
