package co.windly.aac.utilities.di

import co.windly.aac.BuildConfig
import co.windly.aac.data.network.services.AuthorsService
import co.windly.aac.data.network.services.BooksService
import co.windly.aac.data.network.services.CoversService
import co.windly.aac.data.network.services.PublishingHousesService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Singleton

@Module(includes = [NetworkModule::class])
class ApiModule {

  companion object {
    const val API_URL = BuildConfig.API_URL
  }

  @Provides
  @Singleton
  fun provideRetrofit(builder: Retrofit.Builder): Retrofit
    = builder.baseUrl(API_URL).build()

  @Provides
  @Singleton
  fun provideAuthorsService(retrofit: Retrofit): AuthorsService
    = retrofit.create(AuthorsService::class.java)

  @Provides
  @Singleton
  fun provideBooksServices(retrofit: Retrofit): BooksService
    = retrofit.create(BooksService::class.java)

  @Provides
  @Singleton
  fun provideCoversService(retrofit: Retrofit): CoversService
    = retrofit.create(CoversService::class.java)

  @Provides
  @Singleton
  fun providePublishingHousesService(retrofit: Retrofit): PublishingHousesService
    = retrofit.create(PublishingHousesService::class.java)
}
