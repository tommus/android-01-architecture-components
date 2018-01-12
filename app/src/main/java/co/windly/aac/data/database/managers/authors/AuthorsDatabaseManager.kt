package co.windly.aac.data.database.managers.authors

import co.windly.aac.data.database.daos.AuthorsDao
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthorsDatabaseManager @Inject constructor(dao: AuthorsDao)
