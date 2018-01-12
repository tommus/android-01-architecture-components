package co.windly.aac.utilities.rx

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AacSchedulerProvider : SchedulerProvider {

  override fun ui(): Scheduler {
    return AndroidSchedulers.mainThread()
  }

  override fun computation(): Scheduler {
    return Schedulers.computation()
  }

  override fun io(): Scheduler {
    return Schedulers.io()
  }
}
