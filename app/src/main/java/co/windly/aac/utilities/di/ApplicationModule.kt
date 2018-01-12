package co.windly.aac.utilities.di

import co.windly.aac.data.AacDataManager
import co.windly.aac.data.DataManager
import co.windly.aac.utilities.rx.AacSchedulerProvider
import co.windly.aac.utilities.rx.SchedulerProvider
import dagger.Module
import dagger.Provides
import org.modelmapper.ModelMapper
import javax.inject.Singleton

@Module(includes = [
  ApiModule::class, DatabaseModule::class
])
class ApplicationModule {

  @Provides
  @Singleton
  fun provideModelMapper(): ModelMapper {
    return ModelMapper().apply {
      this.configuration.isAmbiguityIgnored = true
    }
  }

  @Provides
  fun provideSchedulerProvider(): SchedulerProvider
    = AacSchedulerProvider()

  @Provides
  @Singleton
  fun provideDataManager(dataManager: AacDataManager): DataManager
    = dataManager
}
