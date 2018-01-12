package co.windly.aac.ui.main.authors

import co.windly.aac.ui.main.authors.list.AuthorsListFragment
import co.windly.aac.ui.main.authors.list.AuthorsListModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class AuthorsFragmentProvider {

  @ContributesAndroidInjector(modules = [AuthorsListModule::class])
  abstract fun provideAuthorsListFragmentFactory(): AuthorsListFragment
}
