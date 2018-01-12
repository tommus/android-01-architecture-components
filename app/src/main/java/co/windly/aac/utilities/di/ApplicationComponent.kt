package co.windly.aac.utilities.di

import android.app.Application
import co.windly.aac.AacApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidSupportInjectionModule::class,
  ApplicationModule::class,
  ActivityBuilder::class
])
interface ApplicationComponent {

  @Component.Builder
  interface Builder {

    @BindsInstance
    fun application(application: Application): Builder

    fun databaseModule(module: DatabaseModule): Builder

    fun apiModule(module: ApiModule): Builder

    fun build(): ApplicationComponent
  }

  fun inject(application: AacApplication)
}
