package co.windly.aac.utilities.di

import co.windly.aac.data.network.interceptors.HeaderAcceptInterceptor
import co.windly.aac.data.network.interceptors.HeaderContentTypeInterceptor
import com.facebook.stetho.okhttp3.StethoInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
class NetworkModule {

  companion object {
    private val APPLICATION_JSON = "application/json"
  }

  //region Retrofit

  @Provides
  @Singleton
  fun provideRetrofitBuilder(
    httpClient: OkHttpClient, callAdapterFactory: CallAdapter.Factory, converterFactory: Converter.Factory):
    Retrofit.Builder {
    return Retrofit.Builder()
      .client(httpClient)
      .addCallAdapterFactory(callAdapterFactory)
      .addConverterFactory(converterFactory)
  }

  //endregion

  //region Http

  @Provides
  @Singleton
  fun provideOkHttpClient(
    @Named("debug") builder: OkHttpClient.Builder,
    @Named("content-type") headerContentTypeInterceptor: Interceptor,
    @Named("accept") headerAcceptInterceptor: Interceptor
  ): OkHttpClient {
    return builder.apply {
      this.addInterceptor(headerContentTypeInterceptor)
      this.addInterceptor(headerAcceptInterceptor)
    }.build()
  }

  @Provides
  @Singleton
  @Named("debug")
  fun provideDebugOkHttpClient(@Named("raw") builder: OkHttpClient.Builder): OkHttpClient.Builder {
    return builder.apply {
      this.addNetworkInterceptor(HttpLoggingInterceptor().apply { this.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY })
      this.addNetworkInterceptor(StethoInterceptor())
    }
  }

  @Provides
  @Singleton
  @Named("raw")
  fun provideRawOkHttpClientBuilder(): OkHttpClient.Builder
    = OkHttpClient.Builder()
    .connectTimeout(15, TimeUnit.SECONDS)
    .readTimeout(20, TimeUnit.SECONDS)
    .writeTimeout(20, TimeUnit.SECONDS)

  //endregion

  //region Interceptors

  @Provides
  @Singleton
  @Named("content-type")
  fun provideHeaderContentTypeInterceptor(): Interceptor
    = HeaderContentTypeInterceptor(APPLICATION_JSON)

  @Provides
  @Singleton
  @Named("accept")
  fun provideHeaderAcceptInterceptor(): Interceptor
    = HeaderAcceptInterceptor(APPLICATION_JSON)

  //endregion

  //region Factories

  @Provides
  @Singleton
  fun provideCallAdapterFactory(): CallAdapter.Factory
    = RxJava2CallAdapterFactory.create()

  @Provides
  @Singleton
  fun provideConverterFactory(): Converter.Factory
    = JacksonConverterFactory.create()

  //endregion
}
