package co.windly.aac.utilities.di

import co.windly.aac.ui.main.authors.AuthorsFragmentProvider
import co.windly.aac.ui.main.books.BooksFragmentProvider
import co.windly.aac.ui.main.covers.CoversFragmentProvider
import co.windly.aac.ui.main.MainActivity
import co.windly.aac.ui.main.MainActivityModule
import co.windly.aac.ui.main.publishinghouses.PublishingHousesFragmentProvider
import co.windly.aac.ui.splash.SplashActivity
import co.windly.aac.ui.splash.SplashActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

  @ContributesAndroidInjector(modules = [SplashActivityModule::class])
  abstract fun bindSplashActivity(): SplashActivity

  @ContributesAndroidInjector(
    modules = [
      MainActivityModule::class,
      AuthorsFragmentProvider::class,
      BooksFragmentProvider::class,
      CoversFragmentProvider::class,
      PublishingHousesFragmentProvider::class
    ])
  abstract fun bindMainActivity(): MainActivity
}
