package co.windly.aac.data.database.managers.books

import co.windly.aac.data.database.daos.BooksDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BooksDatabaseManager @Inject constructor(dao: BooksDao)
