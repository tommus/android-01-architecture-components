package co.windly.aac.utilities

import co.windly.aac.BuildConfig
import timber.log.Timber

class AacLogger private constructor() {

  companion object {

    fun init() {
      if (BuildConfig.DEBUG) {
        Timber.plant(Timber.DebugTree())
      }
    }

    fun d(text: String, vararg objects: Any) {
      Timber.d(text, *objects)
    }

    fun d(throwable: Throwable, text: String, vararg objects: Any) {
      Timber.d(throwable, text, *objects)
    }

    fun i(text: String, vararg objects: Any) {
      Timber.i(text, *objects)
    }

    fun i(throwable: Throwable, text: String, vararg objects: Any) {
      Timber.i(throwable, text, *objects)
    }

    fun w(text: String, vararg objects: Any) {
      Timber.w(text, *objects)
    }

    fun w(throwable: Throwable, text: String, vararg objects: Any) {
      Timber.w(throwable, text, *objects)
    }

    fun e(text: String, vararg objects: Any) {
      Timber.e(text, *objects)
    }

    fun e(throwable: Throwable, text: String, vararg objects: Any) {
      Timber.e(throwable, text, *objects)
    }
  }
}