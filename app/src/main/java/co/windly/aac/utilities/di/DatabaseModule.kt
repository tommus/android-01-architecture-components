package co.windly.aac.utilities.di

import android.app.Application
import android.arch.persistence.room.Room
import co.windly.aac.data.database.AndroidDatabase
import co.windly.aac.data.database.daos.*
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

  private val database: AndroidDatabase

  constructor(application: Application) {
    this.database = Room.databaseBuilder(
      application, AndroidDatabase::class.java, AndroidDatabase.DATABASE_NAME).build()
  }

  @Provides
  @Singleton
  fun provideAuthorsDao(): AuthorsDao
    = this.database.authorsDao()

  @Provides
  @Singleton
  fun provideBooksDao(): BooksDao
    = this.database.booksDao()

  @Provides
  @Singleton
  fun provideCoversDao(): CoversDao
    = this.database.coversDao()

  @Provides
  @Singleton
  fun providePublishingHousesDao(): PublishingHousesDao
    = this.database.publishingHousesDao()

  @Provides
  @Singleton
  fun provideDatabaseDao(): AllDao
    = this.database.allDao()
}
